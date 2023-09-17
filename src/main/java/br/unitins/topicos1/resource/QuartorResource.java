package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.model.QuartoHotel;
import br.unitins.topicos1.repository.QuartoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/quartoHotel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuartorResource {

    @Inject
    QuartoRepository repository;

    @POST
    @Transactional
    public QuartoHotel insert(QuartoHotel quartoHotel) {
        QuartoHotel novoQuarto = new QuartoHotel();
        novoQuarto.setNumero(quartoHotel.getNumero());
        novoQuarto.setDescricao(quartoHotel.getDescricao());

        repository.persist(novoQuarto);
        return novoQuarto;
    }

    @GET
    public List<QuartoHotel> findAll() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public QuartoHotel findbyId(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @GET
    @Path("/search/descricao/{descricao}")
    public List<QuartoHotel> findById(@PathParam("descricao") String descricao) {
        return repository.findByDescricao(descricao);
    }

    @PUT
    @Path("/update/quarto/{id}")
    @Transactional
    public QuartoHotel update(@PathParam("id") Long id, QuartoHotel quartoAtt) {
        QuartoHotel quartoExiste = repository.findById(id);
        if (quartoExiste == null)
            throw new NotFoundException("Quarto não encontrado");

        quartoExiste.setNumero(quartoAtt.getNumero());
        quartoExiste.setDescricao(quartoAtt.getDescricao());

        repository.persist(quartoExiste);
        return quartoExiste;
    }

    @DELETE
    @Path("/delete/quarto/{id}")
    @Transactional
    public QuartoHotel delete(@PathParam("id") Long id) {
        QuartoHotel quartoDelete = repository.findById(id);
        if (quartoDelete == null)
            throw new NotFoundException("Quarto não encontrado");

        repository.delete(quartoDelete);
        return quartoDelete;
    }

}
