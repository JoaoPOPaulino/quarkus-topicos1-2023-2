package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.service.TelefoneService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/telefones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TelefoneResource {

    @Inject
    TelefoneService telefoneService;

    @POST
    public TelefoneResponseDTO insert(TelefoneDTO telefoneDTO, Long idUser) {
        return telefoneService.insert(telefoneDTO, idUser);
    }

    @GET
    @Path("/{idUser}")
    public List<TelefoneResponseDTO> findByUser(@PathParam("idUser") Long idUser) {
        return telefoneService.findByUser(idUser);
    }

    // Implemente os outros endpoints para o CRUD de telefone
}