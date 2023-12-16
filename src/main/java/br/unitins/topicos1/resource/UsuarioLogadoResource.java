package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.topicos1.dto.usuario.EnderecoDTO;
import br.unitins.topicos1.dto.usuario.TelefoneDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.EmailUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.EnderecoUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.LoginUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.NomeUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.SenhaUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.TelefoneUpdateDTO;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioLogadoResource.class);

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService service;

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response getUsuario() {
        String login = jwt.getSubject();
        LOGGER.info("Obtendo usuário para login: " + login);
        return Response.ok(service.findByLogin(login)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("/update/nome/")
    public Response updateNome(@Valid NomeUpdateDTO dto, Long idUsuario) {
        String login = jwt.getSubject();
        LOGGER.info("Atualizando nome do usuário: " + login);
        Long id = service.findByLogin(login).id();
        LOGGER.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateNome(dto, id)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("/update/login/")
    public Response updateLogin(@Valid LoginUpdateDTO dto, Long idUsuario) {
        String login = jwt.getSubject();
        LOGGER.info("Atualizando login do usuário: " + login);
        Long id = service.findByLogin(login).id();
        LOGGER.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateLogin(dto, id)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("/update/email/")
    public Response updateEmail(@Valid EmailUpdateDTO dto, Long idUsuario) {
        String login = jwt.getSubject();
        LOGGER.info("Atualizando email do usuário: " + login);
        Long id = service.findByLogin(login).id();
        LOGGER.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateEmail(dto, id)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("/update/senha/")
    public Response updateSenha(@Valid SenhaUpdateDTO dto, Long idUsuario) {
        String login = jwt.getSubject();
        LOGGER.info("Atualizando senha do usuário: " + login);
        Long id = service.findByLogin(login).id();
        LOGGER.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateSenha(dto, id)).build();
    }

    @POST
    @RolesAllowed({ "User", "Admin" })
    @Path("/insert/telefone/")
    public Response insertTelefone(@Valid TelefoneDTO dto) {
        String login = jwt.getSubject();
        LOGGER.info("Inserindo novo telefone para o usuário: " + login);
        Long id = service.findByLogin(login).id();
        LOGGER.info("Novo telefone inserido com sucesso.");
        return Response.status(200).entity(service.insertTelefone(dto, id)).build();
    }

    @POST
    @RolesAllowed({ "User", "Admin" })
    @Path("/insert/endereco/")
    public Response insertEndereco(@Valid EnderecoDTO dto) {
        String login = jwt.getSubject();
        LOGGER.info("Inserindo novo endereço para o usuário: " + login);
        Long id = service.findByLogin(login).id();
        LOGGER.info("Novo endereço inserido com sucesso.");
        return Response.status(200).entity(service.insertEndereco(dto, id)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("/update/telefone/")
    public Response updateTelefone(@Valid TelefoneUpdateDTO dto, Long idUsuario) {
        String login = jwt.getSubject();
        LOGGER.info("Atualizando telefone do usuário: " + login);
        Long id = service.findByLogin(login).id();
        LOGGER.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateTelefone(dto, id)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("/update/endereco/")
    public Response updateEndereco(@Valid EnderecoUpdateDTO dto, Long idUsuario) {
        String login = jwt.getSubject();
        LOGGER.info("Atualizando endereço do usuário: " + login);
        Long id = service.findByLogin(login).id();
        LOGGER.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateEndereco(dto, id)).build();
    }

    @DELETE
    @RolesAllowed({ "User", "Admin" })
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        String loginUsuarioLogado = jwt.getSubject();
        UsuarioResponseDTO usuarioLogado = service.findByLogin(loginUsuarioLogado);

        if (usuarioLogado.perfil().equals(Perfil.ADMIN) || usuarioLogado.id().equals(id)) {
            LOGGER.info("Deletando usuario do ID: " + id);
            service.delete(id);
            LOGGER.info("Usuario deletado");
            return Response.noContent().build();
        } else {
            LOGGER.info("Tentativa de deletar conta de outro usuário: " + id);
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Acesso negado: não é permitido deletar a conta de outro usuário.").build();
        }
    }

    @GET
    @Path("/search/usuario/all")
    @RolesAllowed({ "Admin" })
    public Response findByAll() {
        LOGGER.info("Buscando todos os usuários");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/search/usuario/nome")
    @RolesAllowed({ "Admin" })
    public Response findByNome(@PathParam("nome") String nome) {
        LOGGER.info("Buscando usuário por nome: " + nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/search/usuario/id")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Buscando usuário por ID: " + id);
        return Response.ok(service.findById(id)).build();
    }

    @PUT
    @RolesAllowed({ "Admin" })
    @Path("/update/perfil/{id}/{perfilId}")
    public Response updatePerfil(@PathParam("id") Long id, @PathParam("perfilId") Integer perfilId) {
        LOGGER.info("Atualizando perfil do usuário com ID: " + id);
        try {
            UsuarioResponseDTO updatedUser = service.updatePerfil(id, perfilId);
            return Response.status(Response.Status.OK).entity(updatedUser).build();
        } catch (IllegalArgumentException e) {
            LOGGER.error("Erro ao atualizar perfil: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Perfil inválido.").build();
        } catch (NotFoundException e) {
            LOGGER.error("Erro ao atualizar perfil: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado.").build();
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar perfil: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar perfil.")
                    .build();
        }
    }
}