package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.MetodoPagamento;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Reserva;

public record PagamentoResponseDTO(
        Long id,
        MetodoPagamento metodoPagamento,
        double valor,
        Reserva reservaId) {

    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getMetodoPagamento(),
                pagamento.getValor(),
                pagamento.getReservaId());
    }

}
