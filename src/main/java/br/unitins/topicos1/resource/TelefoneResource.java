package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.service.TelefoneService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService service;

    @POST
    public Response insert(TelefoneDTO dto, Long userId) {
        return Response.status(Status.CREATED).entity(service.insert(dto, userId)).build();
    }

    @PUT
    @Path("/{idUser}")
    public Response update(TelefoneDTO dto, @PathParam("idUser") Long idUser) {
        service.update(dto, idUser);
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{idUser}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{idUser}")
    public List<TelefoneResponseDTO> findByUser(@PathParam("idUser") Long idUser) {
        return service.findByUser(idUser);
    }

}