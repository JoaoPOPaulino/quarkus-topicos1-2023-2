package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto;

public interface QuartoService {
    QuartoResponseDTO insert(QuartoDTO dto);

    QuartoResponseDTO update(QuartoDTO dto, Long id);

    void delete(Long id);

    QuartoResponseDTO findById(Long id);

    List<QuartoResponseDTO> findAll();

    List<QuartoResponseDTO> findByTipo(Quarto tipo);
}
