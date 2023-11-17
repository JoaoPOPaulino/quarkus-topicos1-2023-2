package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

@Path("/comentarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComentarioResource {

    @Inject
    ComentarioService service;

    @POST
    public Response insert(ComentarioDTO dto){
        return Response.status(status.CREATED).entity(service.insert(dto).build)();

    }

    @PUT
    @Transactional
    public Response update(ComentarioDTO dto, @PathParam("id)")Long id){
}       service.update(dto, id);
        return Response.noContent().build();
}