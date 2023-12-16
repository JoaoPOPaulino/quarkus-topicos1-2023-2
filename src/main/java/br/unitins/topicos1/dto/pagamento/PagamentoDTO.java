package br.unitins.topicos1.dto.pagamento;

import br.unitins.topicos1.dto.tipo.TipoPagamentoDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PagamentoDTO(
                @NotNull(message = "O ID da reserva não pode ser nulo.") @Positive(message = "O ID da reserva deve ser positivo.") Long idReserva,

                @NotNull(message = "O ID do tipo de pagamento não pode ser nulo.") TipoPagamentoDTO tipoPagamento) {
}
