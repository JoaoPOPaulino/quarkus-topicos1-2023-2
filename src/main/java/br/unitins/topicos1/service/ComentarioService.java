package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ComentarioDTO;
import br.unitins.topicos1.dto.ComentarioResponseDTO;
import jakarta.validation.Valid;

public interface ComentarioService {

    ComentarioResponseDTO insert(@Valid ComentarioDTO dto);

    ComentarioResponseDTO update(@Valid ComentarioDTO dto, Long id);

    void delete(Long id);

    ComentarioResponseDTO findById(Long id);

    List<ComentarioResponseDTO> findAll();
}