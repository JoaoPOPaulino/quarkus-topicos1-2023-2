package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ServicoDTO;
import br.unitins.topicos1.dto.ServicoResponseDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface ServicoService {
    ServicoResponseDTO insert(@Valid ServicoDTO dto);

    ServicoResponseDTO update(@Valid ServicoDTO dto, Long id);

    void delete(Long id);

    UsuarioResponseDTO findById(Long id);

    List<ServicoResponseDTO> findByNome(String nome);

    List<ServicoResponseDTO> findByAll();
}
