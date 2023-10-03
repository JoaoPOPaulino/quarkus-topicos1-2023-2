package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.repository.QuartoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class QuartoServiceImpl implements QuartoService {

    @Inject
    public QuartoRepository repository;

    @Override
    @Transactional
    public QuartoResponseDTO insert(QuartoDTO dto) {
        Quarto novoQuarto = new Quarto();
        novoQuarto.setNumero(dto.getNumero());
        novoQuarto.setTipo(Quarto.TipoQuarto.valueOf(dto.getTipo()));
        novoQuarto.setPreco(dto.getPreco());
        novoQuarto.setDisponivel(dto.getDisponivel());

        repository.persist(novoQuarto);

        return QuartoResponseDTO.valueOf(novoQuarto);
    }

    @Override
    @Transactional
    public QuartoResponseDTO update(QuartoDTO dto, Long id) {
        Quarto quarto = repository.findById(id);

        if (quarto != null) {
            quarto.setNumero(dto.getNumero());
            quarto.setTipo(Quarto.TipoQuarto.valueOf(dto.getTipo()));
            quarto.setPreco(dto.getPreco());
            quarto.setDisponivel(dto.getDisponivel());
        } else
            throw new NotFoundException();

        return QuartoResponseDTO.valueOf(quarto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public QuartoResponseDTO findById(Long id) {
        return QuartoResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<QuartoResponseDTO> findByTipo(Quarto.TipoQuarto tipo) {
        return repository.findByTipo(tipo).stream()
                .map(e -> QuartoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<QuartoResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> QuartoResponseDTO.valueOf(e)).toList();
    }
}
