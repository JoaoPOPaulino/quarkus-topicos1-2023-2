package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.QuartoHotel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuartoRepository implements PanacheRepository<QuartoHotel> {

    public List<QuartoHotel> findByTipo(String tipo) {
        return find("UPPER(tipo) LIKE UPPER(?1) ", "%" + tipo + "%").list();
    }

}
