package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.service.ReservaService;
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
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/reservas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaResource {

    @Inject
    ReservaService service;

    @Inject
    JsonWebToken jwt;

    @POST
    public Response insert(@Valid ReservaDTO dto) {
        if (dto.dataI().isAfter(dto.dateF())) {
            throw new WebApplicationException("Data de início deve ser antes da data de fim.", Status.BAD_REQUEST);
        }

        String login = jwt.getSubject();
        

        ReservaResponseDTO reserva = service.insert(dto);
        return Response.status(Status.CREATED).entity(reserva).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid ReservaDTO dto, @PathParam("id") Long id) {
        if (dto.dataI().isAfter(dto.dateF())) {
            throw new WebApplicationException("Data de início deve ser antes da data de fim.", Status.BAD_REQUEST);
        }
        ReservaResponseDTO reserva = service.update(dto, id);
        return Response.ok(reserva).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public List<ReservaResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        ReservaResponseDTO dto = service.findById(id);
        if (dto == null) {
            throw new WebApplicationException("Reserva não encontrada.", Status.NOT_FOUND);
        }
        return Response.ok(dto).build();
    }
}
