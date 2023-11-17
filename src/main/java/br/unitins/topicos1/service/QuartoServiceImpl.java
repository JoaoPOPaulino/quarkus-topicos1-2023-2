package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.TipoQuarto;
import br.unitins.topicos1.repository.QuartoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class QuartoServiceImpl implements QuartoService {

    @Inject
    QuartoRepository repository;

    @Override
    @Transactional
    public QuartoResponseDTO insert(QuartoDTO dto) {
        Quarto novoQuarto = new Quarto();
        novoQuarto.setTipoQuarto(TipoQuarto.valueOf(dto.tipoQuarto().id()));
        novoQuarto.setNumero(dto.numero());
        novoQuarto.setPreco(dto.preco());
        novoQuarto.setDisponivel(dto.disponivel());

        repository.persist(novoQuarto);

        return QuartoResponseDTO.valueOf(novoQuarto);
    }

    @Override
    @Transactional
    public QuartoResponseDTO update(QuartoDTO dto, Long id) {
        Quarto quarto = repository.findById(id);

        if (quarto != null) {
            quarto.setTipoQuarto(TipoQuarto.valueOf(dto.tipoQuarto().id()));
            quarto.setNumero(dto.numero());
            quarto.setPreco(dto.preco());
            quarto.setDisponivel(dto.disponivel());
        } else {
            throw new NotFoundException();
        }

        return QuartoResponseDTO.valueOf(quarto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Quarto quarto = repository.findById(id);
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Quarto não encontrado.");
        }
        repository.delete(quarto);
    }

    @Override
    public QuartoResponseDTO findById(Long id) {
        Quarto quarto = repository.findById(id);

        if (quarto == null) {
            throw new NotFoundException();
        }

        return QuartoResponseDTO.valueOf(quarto);
    }

    @Override
    public List<QuartoResponseDTO> findByTipo(TipoQuarto tipo) {
        List<Quarto> quartos = repository.findByTipo(tipo);
        return quartos.stream()
                .map(QuartoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuartoResponseDTO> findAll() {
        List<Quarto> quartos = repository.listAll();
        return quartos.stream()
                .map(QuartoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}