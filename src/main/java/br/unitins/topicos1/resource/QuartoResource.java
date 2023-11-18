package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.TipoQuarto;
import br.unitins.topicos1.service.QuartoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/quartos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuartoResource {

    @Inject
    QuartoService service;

    @POST
    public Response insert(@Valid QuartoDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid QuartoDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.noContent().build();
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
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        QuartoResponseDTO response = service.findById(id);
        return Response.ok(response).build();
    }

    @GET
    @Path("/serach/tipoQuarto/{tipoQuarto}")
    public Response findByTipo(@PathParam("tipoQuarto") TipoQuarto tipoQuarto) {
        return Response.ok(service.findByTipo(tipoQuarto)).build();
    }

}
