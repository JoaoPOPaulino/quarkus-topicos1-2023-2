package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento;
import java.time.LocalDateTime;

public record PagamentoResponseDTO(
        Long id,
        LocalDateTime dataPagamento,
        ReservaResponseDTO reserva,
        Double valor) {
    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getDataPagamento(),
                ReservaResponseDTO.valueOf(pagamento.getReserva()),
                pagamento.getValor());
    }
}
