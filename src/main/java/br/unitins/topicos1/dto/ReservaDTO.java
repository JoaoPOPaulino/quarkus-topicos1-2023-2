package br.unitins.topicos1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record ReservaDTO(
        @FutureOrPresent(message = "A data de início da reserva deve ser igual ou posterior à data atual") LocalDate dataI,
        @FutureOrPresent(message = "A data final da reserva deve ser igual ou posterior à data atual") LocalDate dateF,
        @NotNull(message = "A quantidade não pode ser nula.") Integer quantidade,
        @NotNull(message = "O preço não pode ser nulo.") Double preco,
        @NotNull(message = "O idQuarto não pode ser nulo.") Long idQuarto) {
}
