package br.unitins.topicos1.dto.reserva.reservaUpdate;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataFinalUpdateDTO(
        @FutureOrPresent(message = "A data de  da reserva deve ser igual ou posterior à data atual") @NotNull(message = "A data de início não pode ser nula.") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de data inválido. Use o formato AAAA-MM-DD.") LocalDate dataFinal) {

}
