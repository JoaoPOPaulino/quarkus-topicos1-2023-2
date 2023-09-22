package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Pagamento.TipoPagamento;

public record PagamentoResponseDTO(
        Long id,
        TipoPagamento tipoPagamento, // Assume que TipoPagamento é o nome do Enum
        Long reservaId) {

    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getTipoPagamento(),
                pagamento.getReserva().getId());
    }
}
