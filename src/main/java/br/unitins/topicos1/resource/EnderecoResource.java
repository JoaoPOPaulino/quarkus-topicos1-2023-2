package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService enderecoService;

    @POST
    public Response insert(EnderecoDTO dto) {
        EnderecoResponseDTO responseDTO = enderecoService.insert(dto);
        return Response.status(Status.CREATED).entity(responseDTO).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(EnderecoDTO dto, @PathParam("id") Long id) {
        EnderecoResponseDTO responseDTO = enderecoService.update(dto, id);
        return Response.status(Status.NO_CONTENT).entity(responseDTO).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        enderecoService.delete(id);
    }

    @GET
    public List<EnderecoResponseDTO> findAll() {
        return enderecoService.findAll();
    }

    @GET
    @Path("/{id}")
    public EnderecoResponseDTO findById(@PathParam("id") Long id) {
        return enderecoService.findById(id);
    }
}
