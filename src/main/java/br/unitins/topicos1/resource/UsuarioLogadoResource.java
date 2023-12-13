package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.topicos1.dto.Telefone.TelefoneDTO;
import br.unitins.topicos1.dto.Telefone.TelefoneUpdateDTO;
import br.unitins.topicos1.dto.email.EmailUpdateDTO;
import br.unitins.topicos1.dto.endereco.EnderecoDTO;
import br.unitins.topicos1.dto.endereco.EnderecoUpdateDTO;
import br.unitins.topicos1.dto.login.LoginUpdateDTO;
import br.unitins.topicos1.dto.login.SenhaUpdateDTO;
import br.unitins.topicos1.dto.nome.NomeUpdateDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
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

    private static final Logger logger = LoggerFactory.getLogger(UsuarioLogadoResource.class);

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService service;

    @GET
    @RolesAllowed({ "User", "Admin" })
    public Response getUsuario() {
        String login = jwt.getSubject();
        logger.info("Obtendo usuário para login: {}", login);
        return Response.ok(service.findByLogin(login)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("update/nome/")
    public Response updateNome(@Valid NomeUpdateDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando nome do usuário: {}" + login);
        Long idUsuario = service.findByLogin(login).id();
        logger.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateNome(dto, idUsuario)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("update/login/")
    public Response updateLogin(@Valid LoginUpdateDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando login do usuário: {}" + login);
        Long idUsuario = service.findByLogin(login).id();
        logger.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateLogin(dto, idUsuario)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("update/email/")
    public Response updateEmail(@Valid EmailUpdateDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando email do usuário: {}" + login);
        Long idUsuario = service.findByLogin(login).id();
        logger.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateEmail(dto, idUsuario)).build();
    }

    @PATCH
    @RolesAllowed({ "User", "Admin" })
    @Path("update/senha/")
    public Response updateSenha(@Valid SenhaUpdateDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando senha do usuário: {}" + login);
        Long idUsuario = service.findByLogin(login).id();
        logger.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateSenha(dto, idUsuario)).build();
    }

    @POST
    @RolesAllowed({ "User", "Admin" })
    @Path("insert/telefone/")
    public Response insertTelefone(@Valid TelefoneDTO dto) {
        String login = jwt.getSubject();
        logger.info("Inserindo novo telefone para o usuário: {}" + login);
        Long idUsuario = service.findByLogin(login).id();
        logger.info("Novo telefone inserido com sucesso.");
        return Response.status(200).entity(service.insertTelefone(dto, idUsuario)).build();
    }

    @PUT
    @RolesAllowed({ "User", "Admin" })
    @Path("update/telefone/")
    public Response updateTelefone(@Valid TelefoneUpdateDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando telefone do usuário: {}" + login);
        Long idUsuario = service.findByLogin(login).id();
        logger.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateTelefone(dto, idUsuario)).build();
    }

    @PUT
    @RolesAllowed({ "User", "Admin" })
    @Path("update/endereco/")
    public Response updateEndereco(@Valid EnderecoUpdateDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando endereço do usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        logger.info("Atualizado com sucesso.");
        return Response.status(200).entity(service.updateEndereco(dto, idUsuario)).build();
    }

    @DELETE
    @RolesAllowed({ "User", "Admin" })
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        logger.info("Deletando usuario do ID: {}" + id);
        service.delete(id);
        logger.info("Usuario deletado");
        return Response.noContent().build();
    }

    @GET
    @Path("/search/usuario/all")
    @RolesAllowed({ "Admin" })
    public Response findByAll() {
        logger.info("Buscando todos os usuários");
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/search/usuario/nome")
    @RolesAllowed({ "Admin" })
    public Response findByNome(@PathParam("nome") String nome) {
        logger.info("Buscando usuário por nome: {}" + nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/search/usuario/id")
    @RolesAllowed({ "Admin" })
    public Response findById(@PathParam("id") Long id) {
        logger.info("Buscando usuário por ID: {}" + id);
        return Response.ok(service.findById(id)).build();
    }

    @PUT
    @RolesAllowed({ "Admin" })
    @Path("/update/perfil/{id}/{perfilId}")
    public Response updatePerfil(@PathParam("id") Long id, @PathParam("perfilId") Integer perfilId) {
        logger.info("Atualizando perfil do usuário com ID: {}" + id);
        try {
            UsuarioResponseDTO updatedUser = service.updatePerfil(id, perfilId);
            return Response.status(Response.Status.OK).entity(updatedUser).build();
        } catch (IllegalArgumentException e) {
            logger.error("Erro ao atualizar perfil: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Perfil inválido.").build();
        } catch (NotFoundException e) {
            logger.error("Erro ao atualizar perfil: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado.").build();
        } catch (Exception e) {
            logger.error("Erro ao atualizar perfil: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno ao atualizar perfil.")
                    .build();
        }
    }
}