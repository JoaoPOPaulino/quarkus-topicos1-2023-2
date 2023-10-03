package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;

public interface TelefoneService {

    TelefoneResponseDTO insert(TelefoneDTO dto);

    TelefoneResponseDTO update(TelefoneDTO dto, Long id);

    void delete(Long id);

    TelefoneResponseDTO findById(Long id);

    List<TelefoneResponseDTO> findByAll();
}
