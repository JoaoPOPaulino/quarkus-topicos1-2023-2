package br.unitins.topicos1.service.pagamento;

import br.unitins.topicos1.dto.pagamento.PagamentoDTO;
import br.unitins.topicos1.dto.pagamento.PagamentoResponseDTO;
import jakarta.validation.Valid;

public interface PagamentoService {
    PagamentoResponseDTO insert(@Valid PagamentoDTO dto);

    PagamentoResponseDTO update(@Valid PagamentoDTO dto, Long id);

    void delete(Long id);

    PagamentoResponseDTO findById(Long id);
}
