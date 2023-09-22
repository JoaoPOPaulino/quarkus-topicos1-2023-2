package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/enderecos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService service;

    @POST
    public EnderecoResponseDTO insert(EnderecoDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @Path("/{id}")
    public EnderecoResponseDTO update(@PathParam("id") Long id, EnderecoDTO dto) {
        return service.update(dto, id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    public List<EnderecoResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    public EnderecoResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
}
