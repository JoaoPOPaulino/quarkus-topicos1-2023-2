package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.model.TipoPagamento;

import java.time.LocalDateTime;

public record PagamentoResponseDTO(
        Long id,
        LocalDateTime dataPagamento,
        Reserva reserva,
        TipoPagamento tipoPagamento,
        Double valor) {

    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getDataPagamento(),
                pagamento.getReserva(),
                pagamento.getTipoPagamento(),
                pagamento.getValor());
    }
}
