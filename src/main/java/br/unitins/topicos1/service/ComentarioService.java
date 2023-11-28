package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ComentarioDTO;
import br.unitins.topicos1.dto.ComentarioResponseDTO;

public interface ComentarioService {
    ComentarioResponseDTO insert(ComentarioDTO dto);

    List<ComentarioResponseDTO> findAll();

    ComentarioResponseDTO findById(Long id);

    ComentarioResponseDTO update(ComentarioDTO dto, Long id);

    void delete(Long id);
}