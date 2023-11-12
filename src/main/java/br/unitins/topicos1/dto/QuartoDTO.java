package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;

public record QuartoDTO(
        @NotNull(message = "O campo idTipo não pode ser nulo.") Integer idTipo,
        @NotNull(message = "O campo número não pode ser nulo.") Integer numero,
        @NotNull(message = "O campo preço não pode ser nulo.") Double preco,
        @NotNull(message = "O campo disponível não pode ser nulo.") Boolean disponivel) {
}
