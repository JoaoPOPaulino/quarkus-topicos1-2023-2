package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.TipoQuarto;
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

@Path("/Quarto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuartoResource {

    @Inject
    QuartoRepository repository;

    @POST
    @Transactional
    public Quarto insert(Quarto Quarto) {
        Quarto novoQuarto = new Quarto();
        novoQuarto.setNumero(Quarto.getNumero());
        novoQuarto.setTipo(Quarto.getTipo());

        repository.persist(novoQuarto);
        return novoQuarto;
    }

    @GET
    public List<Quarto> findAll() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Quarto findbyId(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @GET
    @Path("/search/tipo/{tipo}")
    public List<Quarto> findById(@PathParam("tipo") TipoQuarto tipo) {
        return repository.findByTipo(tipo);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Quarto update(@PathParam("id") Long id, Quarto quartoAtt) {
        Quarto quartoExiste = repository.findById(id);
        if (quartoExiste == null)
            throw new NotFoundException("Quarto não encontrado");

        quartoExiste.setNumero(quartoAtt.getNumero());
        quartoExiste.setTipo(quartoAtt.getTipo());

        repository.persist(quartoExiste);
        return quartoExiste;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Quarto delete(@PathParam("id") Long id) {
        Quarto quartoDelete = repository.findById(id);
        if (quartoDelete == null)
            throw new NotFoundException("Quarto não encontrado");

        repository.delete(quartoDelete);
        return quartoDelete;
    }

}
