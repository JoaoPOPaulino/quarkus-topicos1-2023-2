package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Reserva;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record ReservaDTO(
                @FutureOrPresent(message = "A data de início da reserva deve ser igual ou posterior à data atual") @NotNull(message = "A data de início não pode ser nula.") LocalDate dataI,
                @FutureOrPresent(message = "A data final da reserva deve ser igual ou posterior à data atual") @NotNull(message = "A data de fim não pode ser nula.") LocalDate dateF,
                Integer quantidade,
                Double preco,
                @NotNull(message = "O idQuarto não pode ser nulo.") Long idQuarto,
                @NotNull(message = "O idUsuario não pode ser nulo.") Long idUsuario) {
        public static ReservaDTO valueOf(Reserva reserva) {
                return new ReservaDTO(
                                reserva.getDataIncio(),
                                reserva.getDataFim(),
                                reserva.getQuantidade(),
                                reserva.getPreco(),
                                reserva.getQuarto().getId(),
                                reserva.getUsuario().getId());
        }
}
