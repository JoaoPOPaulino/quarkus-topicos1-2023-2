package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.model.MetodoPagamento;

public interface PagamentoService {
    PagamentoResponseDTO insert(PagamentoDTO dto);

    PagamentoResponseDTO update(PagamentoDTO dto, Long id);

    void delete(Long id);

    PagamentoResponseDTO findById(Long id);

    List<PagamentoResponseDTO> findByTipo(MetodoPagamento tipo);

    List<PagamentoResponseDTO> findByAll();

}
