package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.comentario.ComentarioDTO;
import br.unitins.topicos1.dto.comentario.ComentarioResponseDTO;
import br.unitins.topicos1.service.comentario.ComentarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/comentarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComentarioResource {

    @Inject
    ComentarioService service;

    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());

    @POST
    public Response insert(ComentarioDTO dto) {
        LOGGER.info("Inserindo novo comentário");
        Response response = Response.status(Status.CREATED).entity(service.insert(dto)).build();
        LOGGER.info("Comentário inserido com sucesso");
        return response;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(ComentarioDTO dto, @PathParam("id") Long id) {
        LOGGER.info("Atualizando comentário com ID: " + id);
        try {
            ComentarioResponseDTO dtoUpdate = service.update(dto, id);
            LOGGER.info("Comentário com ID: " + id + " atualizado com sucesso");
            return Response.ok(dtoUpdate).build();
        } catch (NotFoundException e) {
            LOGGER.error("Comentário não encontrado para atualização com ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOGGER.info("Excluindo comentário com ID: " + id);
        service.delete(id);
        LOGGER.info("Comentário com ID: " + id + " excluído com sucesso");
        return Response.noContent().build();
    }

    @GET
    public Response findAll() {
        LOGGER.info("Buscando todos os comentários");
        Response response = Response.ok(service.findAll()).build();
        LOGGER.info("Comentários recuperados com sucesso");
        return response;
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOGGER.info("Buscando comentário com ID: " + id);
        ComentarioResponseDTO dto = service.findById(id);
        if (dto == null) {
            LOGGER.error("Comentário não encontrado com ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LOGGER.info("Comentário com ID: " + id + " encontrado com sucesso");
        return Response.ok(dto).build();
    }

}