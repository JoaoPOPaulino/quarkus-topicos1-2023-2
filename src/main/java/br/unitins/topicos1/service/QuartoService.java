package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;

public interface QuartoService {
    QuartoResponseDTO insert(QuartoDTO dto);

    QuartoResponseDTO update(QuartoDTO dto, Long id);

    void delete(Long id);

    QuartoResponseDTO findById(Long id);

    List<QuartoResponseDTO> findByAll();
}
