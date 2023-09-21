package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.service.ReservaService;
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

@Path("/reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaResource {

    @Inject
    ReservaService service;

    @POST
    public ReservaResponseDTO insert(ReservaDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @Path("/{id}")
    public ReservaResponseDTO update(ReservaDTO dto, @PathParam("id") Long id) {
        return service.update(dto, id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    public List<ReservaResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    public ReservaResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }
}
