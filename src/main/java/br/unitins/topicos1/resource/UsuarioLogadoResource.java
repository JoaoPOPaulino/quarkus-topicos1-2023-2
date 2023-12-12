package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.Telefone.TelefoneDTO;
import br.unitins.topicos1.dto.Telefone.TelefoneUpdateDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
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
    @RolesAllowed({ "User" })
    @Path("patch/nome/")
    public Response updateNome(@Valid UsuarioDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando nome do usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        return Response.status(200).entity(service.updateNome(dto, idUsuario)).build();
    }

    @PATCH
    @RolesAllowed({ "User" })
    @Path("patch/login/")
    public Response updateLogin(@Valid UsuarioDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando login do usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        return Response.status(200).entity(service.updateLogin(dto, idUsuario)).build();
    }

    @PATCH
    @RolesAllowed({ "User" })
    @Path("patch/email/")
    public Response updateEmail(@Valid UsuarioDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando email do usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        return Response.status(200).entity(service.updateEmail(dto, idUsuario)).build();
    }

    @PATCH
    @RolesAllowed({ "User" })
    @Path("patch/senha/")
    public Response updateSenha(@Valid UsuarioDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando senha do usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        return Response.status(200).entity(service.updateSenha(dto, idUsuario)).build();
    }

    @POST
    @RolesAllowed({ "User" })
    @Path("post/telefone/")
    public Response insertTelefone(@Valid TelefoneDTO dto) {
        String login = jwt.getSubject();
        logger.info("Inserindo novo telefone para o usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        return Response.status(200).entity(service.insertTelefone(dto, idUsuario)).build();
    }

    @PUT
    @RolesAllowed({ "User", })
    @Path("put/telefone/")
    public Response updateTelefone(@Valid TelefoneUpdateDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando telefone do usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        return Response.status(200).entity(service.updateTelefone(dto, idUsuario)).build();
    }

    @PUT
    @RolesAllowed({ "User" })
    @Path("put/endereco/")
    public Response updateEndereco(@Valid EnderecoDTO dto) {
        String login = jwt.getSubject();
        logger.info("Atualizando endereço do usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        return Response.status(200).entity(service.updateEndereco(dto, idUsuario)).build();
    }

    @DELETE
    @RolesAllowed({ "User" })
    @Path("/delete/")
    public Response deleteCliente() {
        String login = jwt.getSubject();
        logger.info("Deletando usuário: {}", login);
        Long idUsuario = service.findByLogin(login).id();
        service.delete(idUsuario);
        return Response.ok().build();
    }

}