package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.service.reserva.ReservaService;
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
import jakarta.ws.rs.NotFoundException;
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

    private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());

    @POST
    @RolesAllowed({ "User", "Admin" })
    public Response insert(@Valid ReservaDTO dto) {
        try {
            Long usuarioId = Long.parseLong(jwt.getSubject());
            ReservaDTO novaDto = new ReservaDTO(dto.dataInicio(), dto.dataFim(), dto.idQuarto(), usuarioId);

            LOGGER.info("Criando reserva para o usuário ID: " + usuarioId);
            ReservaResponseDTO reservaCriada = service.insert(novaDto);
            LOGGER.info("Reserva criada com sucesso para o usuário ID: " + usuarioId);

            return Response.status(Status.CREATED).entity(reservaCriada).build();
        } catch (NumberFormatException e) {
            LOGGER.error("Erro na conversão do ID do usuário: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Formato de ID inválido.").build();
        } catch (Exception e) {
            LOGGER.error("Erro ao criar reserva: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar reserva.").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({ "User", "Admin" })
    public Response update(@Valid ReservaDTO dto, @PathParam("id") Long id) {
        try {
            Long usuarioId = Long.parseLong(jwt.getSubject());
            ReservaDTO novaDto = new ReservaDTO(dto.dataInicio(), dto.dataFim(), dto.idQuarto(), usuarioId);

            LOGGER.info("Atualizando reserva para o usuário ID: " + usuarioId);
            ReservaResponseDTO reservaAtualizada = service.update(novaDto, id);
            LOGGER.info("Reserva com ID: " + id + " atualizada com sucesso para o usuário ID: " + usuarioId);

            return Response.status(Status.NO_CONTENT).build();
        } catch (NumberFormatException e) {
            LOGGER.error("Erro na conversão do ID do usuário: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Formato de ID inválido.").build();
        } catch (NotFoundException e) {
            LOGGER.error("Reserva não encontrada: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar reserva: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({ "User", "Admin" })
    public Response delete(@PathParam("id") Long id) {
        try {
            Long usuarioId = Long.parseLong(jwt.getSubject());
            ReservaResponseDTO reservaDTO = service.findById(id);

            if (reservaDTO == null) {
                LOGGER.error("Reserva não encontrada.");
                return Response.status(Status.NOT_FOUND).build();
            }

            if (!reservaDTO.usuario().id().equals(usuarioId) && !jwt.getGroups().contains("Admin")) {
                LOGGER.error("Usuário não autorizado a deletar esta reserva.");
                return Response.status(Status.FORBIDDEN).build();
            }

            service.delete(id);
            LOGGER.info("Reserva com ID: " + id + " excluída com sucesso.");
            return Response.noContent().build();
        } catch (NumberFormatException e) {
            LOGGER.error("Erro na conversão do ID do usuário: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Formato de ID inválido.").build();
        } catch (Exception e) {
            LOGGER.error("Erro ao excluir reserva: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor.").build();
        }
    }

    @GET
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
        LOGGER.info("Buscando reserva com ID: " + id);
        Response response = Response.ok(service.findById(id)).build();
        LOGGER.info("Reserva com ID: " + id + " encontrada com sucesso");
        return response;
    }

    @GET
    @Path("/historico")
    @RolesAllowed({ "User", "Admin" })
    public Response historicoReservas() {
        try {
            Long usuarioId = Long.parseLong(jwt.getSubject());
            List<ReservaResponseDTO> reservas = service.findReservaByUsuarioId(usuarioId);
            return Response.ok(reservas).build();
        } catch (NumberFormatException e) {
            LOGGER.error("Erro na conversão do ID do usuário: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Formato de ID inválido.").build();
        } catch (JwtException e) {
            LOGGER.error("Erro no token JWT: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido ou expirado.").build();
        } catch (NoResultException e) {
            LOGGER.error("Nenhum resultado encontrado: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Histórico de reservas não encontrado.").build();
        } catch (PersistenceException e) {
            LOGGER.error("Erro de acesso ao banco de dados: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro no servidor.").build();
        } catch (SecurityException e) {
            LOGGER.error("Acesso negado: " + e.getMessage());
            return Response.status(Response.Status.FORBIDDEN).entity("Acesso negado.").build();
        } catch (Exception e) {
            LOGGER.error("Erro inesperado: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor.").build();
        }
    }
}