package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Pagamento.TipoPagamento;
import br.unitins.topicos1.model.Reserva;

public record PagamentoResponseDTO(
        Long id,
        TipoPagamento tipoPagamento,
        Long reservaId) {

    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }

        Reserva reserva = pagamento.getReserva();
        Long reservaId = (reserva != null) ? reserva.getId() : null;

        return new PagamentoResponseDTO(
                pagamento.getId(), // Adicione o ID aqui
                pagamento.getTipoPagamento(),
                reservaId);
    }

    public Long getId() {
        return id;
    }

}
