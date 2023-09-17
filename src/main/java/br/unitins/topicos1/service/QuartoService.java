package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.QuartoHotelDTO;
import br.unitins.topicos1.dto.QuartoHotelResponseDTO;

public interface QuartoService {
    public QuartoHotelResponseDTO insert(QuartoHotelDTO dto);

    public QuartoHotelResponseDTO update(QuartoHotelDTO dto, Long id);

    public void delete(Long id);

    public QuartoHotelResponseDTO findById(Long id);

    public List<QuartoHotelResponseDTO> findByTipo(String tipo);

    public List<QuartoHotelResponseDTO> findByAll();

}
