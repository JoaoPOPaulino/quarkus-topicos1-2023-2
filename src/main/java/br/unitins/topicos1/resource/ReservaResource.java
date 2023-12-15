package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.service.reserva.ReservaService;
import br.unitins.topicos1.service.usuario.UsuarioService;
import io.smallrye.jwt.build.JwtException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaResource {

    @Inject
    ReservaService service;

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());

    @POST
    @RolesAllowed({ "User", "Admin" })
    public Response insert(@Valid ReservaDTO dto) {

        LOGGER.info("Iniciando a criação de uma nova reserva com data de início: " + dto.dataInicio());
        Response response = Response.status(Status.CREATED).entity(service.insert(dto)).build();
        LOGGER.info("Reserva criada com sucesso");
        return response;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response update(@Valid ReservaDTO dto, @PathParam("id") Long id) {
        LOGGER.info("Iniciando atualização da reserva com ID: {}" + id);
        service.update(dto, id);
        LOGGER.info("Reserva com ID: " + id + " atualizada com sucesso");
        return Response.noContent().build();
    }

    @DELETE
    @RolesAllowed({ "User", "Admin" })
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        String loginUsuarioLogado = jwt.getSubject();
        UsuarioResponseDTO usuarioLogado = usuarioService.findByLogin(loginUsuarioLogado);

        if (usuarioLogado.perfil().equals(Perfil.ADMIN) || usuarioLogado.id().equals(id)) {
            LOGGER.info("Deletando usuario do ID: {}" + id);
            service.delete(id);
            LOGGER.info("Usuario deletado");
            return Response.noContent().build();
        } else {
            LOGGER.info("Tentativa de deletar conta de outro usuário: {}" + id);
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Acesso negado: não é permitido deletar a conta de outro usuário.").build();
        }
    }

    @GET
    @Path("/search/reserva/all")
    @RolesAllowed({ "Admin" })
    public Response findAll() {
        LOGGER.info("Buscando todas as reservas");
        Response response = Response.ok(service.findByAll()).build();
        LOGGER.info("Reservas recuperadas com sucesso");
        return response;
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Buscando reserva com ID: {}" + id);
        Response response = Response.ok(service.findById(id)).build();
        LOGGER.info("Reserva com ID: " + id + " encontrada com sucesso");
        return response;
    }

    @GET
    @Path("/search/historico/{usuarioId}")
    @RolesAllowed({ "User", "Admin" })
    public Response historicoReservas(@PathParam("id") Long id) {
        try {
            LOGGER.info("JWT Subject: " + jwt.getSubject());
            Long usuarioIdAutenticado = Long.parseLong(jwt.getSubject());
            boolean isAdmin = jwt.getGroups().contains("Admin");

            Long usuarioId = isAdmin && id != null ? id : usuarioIdAutenticado;

            List<ReservaResponseDTO> reservas = service.findReservaByUsuarioId(usuarioId);
            return Response.ok(reservas).build();
        } catch (NumberFormatException e) {
            LOGGER.error("Erro na conversão do ID do usuário: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Formato de ID inválido.").build();
        } catch (Exception e) {
            LOGGER.error("Erro: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro no servidor.").build();
        }
    }
}