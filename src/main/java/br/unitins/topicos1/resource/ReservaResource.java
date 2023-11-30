package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.service.reserva.ReservaService;
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

        return Response.status(Status.CREATED).entity(service.insert(dto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@Valid ReservaDTO dto, @PathParam("id") Long id) {
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
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/historico")
    public Response HistoricoReservas() {
        try {
            Long usuarioId = Long.parseLong(jwt.getSubject());
            List<ReservaResponseDTO> reservas = service.findReservaByUsuarioId(usuarioId);
            return Response.ok(reservas).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não autorizado.").build();
        }
    }
}
