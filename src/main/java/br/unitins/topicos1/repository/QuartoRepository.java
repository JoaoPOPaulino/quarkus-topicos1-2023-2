package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;

@ApplicationScoped
public class QuartoRepository implements PanacheRepository<Quarto> {

    public <TipoQuarto> List<Quarto> findByTipo(TipoQuarto tipo) {
        return find("UPPER(tipo) LIKE UPPER(?1) ", "%" + tipo + "%").list();
    }

    public QuartoResponseDTO insert(@Valid QuartoDTO dto) {
        return null;
    }

    public void update(QuartoDTO dto, Long id) {
    }

    public Object findByAll() {
        return null;
    }

    public Object findById() {
        return null;
    }

    public Object findByTipo() {
        return null;
    }

}
