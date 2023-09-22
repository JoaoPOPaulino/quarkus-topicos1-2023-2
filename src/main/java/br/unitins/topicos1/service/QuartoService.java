package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.QuartoResponseDTO;
import br.unitins.topicos1.model.Quarto;

public interface QuartoService {
    QuartoResponseDTO insert(QuartoDTO dto);

    public QuartoResponseDTO update(QuartoDTO dto, Long id);

    public void delete(Long id);

    public QuartoResponseDTO findById(Long id);

    public List<QuartoResponseDTO> findByTipo(Quarto.TipoQuarto tipo);

    public List<QuartoResponseDTO> findByAll();

}
