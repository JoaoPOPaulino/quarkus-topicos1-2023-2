package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.model.QuartoHotel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuartoRepository implements PanacheRepository<QuartoHotel> {

    public List<QuartoHotel> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE UPPER(?1) ", "%" + descricao + "%").list();
    }

}
