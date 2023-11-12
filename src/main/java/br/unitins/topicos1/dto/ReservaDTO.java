package br.unitins.topicos1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;

public record ReservaDTO(
        @FutureOrPresent(message = "A data de início da reserva deve ser igual ou posterior à data atual") LocalDate dataI,
        @FutureOrPresent(message = "A data final da reserva deve ser igual ou posterior à data atual") LocalDate dateF,
        Integer quantidade,
        Double preco,
        Long idQuarto) {
}
