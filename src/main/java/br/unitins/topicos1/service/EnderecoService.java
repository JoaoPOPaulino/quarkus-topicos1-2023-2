package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;

public interface EnderecoService {

    EnderecoResponseDTO insert(EnderecoDTO dto);

    EnderecoResponseDTO update(EnderecoDTO dto, Long id);

    void delete(Long id);

    EnderecoResponseDTO findById(Long id);

    List<EnderecoResponseDTO> findAll();
}
