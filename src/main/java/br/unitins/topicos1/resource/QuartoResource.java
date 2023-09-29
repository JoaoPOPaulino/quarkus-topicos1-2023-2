package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto.TipoQuarto;
import br.unitins.topicos1.repository.QuartoRepository;
import jakarta.inject.Inject;
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

@Path("/quarto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuartoResource {

    @Inject
    QuartoRepository service;

    @POST
    public Response insert(@Valid QuartoDTO dto) {
        QuartoResponseDTO retorno = service.insert(dto);

        return Response.status(201).entity(retorno).build();

    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(QuartoDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findbyId(@PathParam("id") Long id) {
        return Response.ok(service.findById()).build();
    }

    @GET
    @Path("/search/tipo/{tipo}")
    public Response findByTipo(@PathParam("tipo") TipoQuarto tipo) {
        return Response.ok(service.findByTipo()).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.deleteById(id);
        return Response.status(Status.NO_CONTENT).build();
    }

}
