package br.unitins.topicos1.resource;

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

import java.util.List;

import jakarta.inject.Inject;
import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.service.PagamentoService;

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    @Inject
    PagamentoService service;

    @POST
    public Response insert(PagamentoDTO dto) {
        PagamentoResponseDTO responseDTO = service.insert(dto);
        return Response.status(Status.CREATED).entity(responseDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, PagamentoDTO dto) {
        PagamentoResponseDTO responseDTO = service.update(dto, id);
        return Response.status(Status.NO_CONTENT).entity(responseDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    public List<PagamentoResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    public PagamentoResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
}
