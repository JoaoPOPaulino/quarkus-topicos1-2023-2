package br.unitins.topicos1.repository;

import java.util.List;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class QuartoRepository implements PanacheRepository<Quarto> {

    public List<Quarto> findByTipo(Quarto.TipoQuarto tipo) {
        return find("tipo = ?1", tipo).list();
    }

    @Transactional
    public QuartoResponseDTO insert(QuartoDTO dto) {
        Quarto quarto = new Quarto();
        quarto.setNumero(dto.getNumero());
        quarto.setTipo(dto.getTipo());
        quarto.setPreco(dto.getPreco());
        quarto.setDisponivel(dto.isDisponivel());
        persist(quarto);

        return QuartoResponseDTO.valueOf(quarto);
    }

    @Transactional
    public QuartoResponseDTO update(QuartoDTO dto, Long id) {
        Quarto quarto = findById(id);
        if (quarto == null) {
            throw new NotFoundException();
        }
        quarto.setNumero(dto.getNumero());
        quarto.setTipo(dto.getTipo());
        quarto.setPreco(dto.getPreco());
        quarto.setDisponivel(dto.isDisponivel());
        persist(quarto);

        return QuartoResponseDTO.valueOf(quarto);
    }

    public List<Quarto> findByAll() {
        return listAll();
    }
}
