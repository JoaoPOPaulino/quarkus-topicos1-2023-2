package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.service.TelefoneService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService service;

    @POST
    public TelefoneResponseDTO insert(TelefoneDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @Path("/{id}")
    public TelefoneResponseDTO update(TelefoneDTO dto, @PathParam("id") Long id) {
        return service.update(dto, id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    public List<TelefoneResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    public TelefoneResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
}
