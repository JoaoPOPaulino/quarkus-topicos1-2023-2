package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;

import java.util.List;

public interface TelefoneService {
    TelefoneResponseDTO insert(TelefoneDTO dto, Long userId);

    TelefoneResponseDTO update(TelefoneDTO dto, Long telefoneId);

    void delete(Long telefoneId);

    TelefoneResponseDTO findById(Long telefoneId);

    List<TelefoneResponseDTO> findByUser(Long userId);
}
