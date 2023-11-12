package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Quarto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuartoRepository implements PanacheRepository<Quarto> {

}
