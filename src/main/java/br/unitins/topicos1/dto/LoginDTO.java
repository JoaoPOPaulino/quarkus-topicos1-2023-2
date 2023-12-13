package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record LoginDTO(
        @NotEmpty(message = "O campo não pode ser nulo.") String login,

        @NotEmpty(message = "O campo não pode ser nulo.") String senha) {

}
