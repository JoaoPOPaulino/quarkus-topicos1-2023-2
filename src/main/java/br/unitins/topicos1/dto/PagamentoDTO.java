package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PagamentoDTO(
                @NotNull(message = "A data de pagamento não pode ser nula.") LocalDateTime dataPagamento,
                @NotNull(message = "O ID da reserva não pode ser nulo.") Long idReserva,
                @NotNull(message = "O ID do tipo de pagamento não pode ser nulo.") TipoPagamentoDTO tipoPagamento,
                Double valor) {
}
