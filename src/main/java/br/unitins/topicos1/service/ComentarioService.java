package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ComentarioDTO;
import br.unitins.topicos1.resource.ComentarioResource;
import jakarta.validation.Valid;

public interface ComentarioService {

    ComentarioResource insert(@Valid ComentarioDTO dto);

    ComentarioResource update(@Valid ComentarioDTO dto, Long id);

    void delete(Long id);

    ComentarioResource findById(Long id);

    List<ComentarioResource> findAll();
}