package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.comentario.ComentarioDTO;
import br.unitins.topicos1.dto.comentario.ComentarioResponseDTO;
import br.unitins.topicos1.repository.ComentarioRepository;
import br.unitins.topicos1.service.comentario.ComentarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Path("/comentarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComentarioResource {

    @Inject
    ComentarioService service;

    @POST
    public Response insert(ComentarioDTO dto) {

        return Response.status(Status.CREATED).entity(service.insert(dto)).build();

    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(ComentarioDTO dto, @PathParam("id)") Long id) {
        ComentarioResponseDTO dtoUpdate = service.update(dto, id);
        if (dtoUpdate == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dtoUpdate).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Transactional
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        ComentarioResponseDTO dto = service.findById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

}
