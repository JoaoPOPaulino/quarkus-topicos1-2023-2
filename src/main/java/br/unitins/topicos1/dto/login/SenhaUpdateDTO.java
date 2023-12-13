package br.unitins.topicos1.dto.login;

import jakarta.validation.constraints.NotBlank;

public record SenhaUpdateDTO(
        @NotBlank(message = "O campo senhaAntiga não pode ser nulo.") String senha,
        @NotBlank(message = "O campo senhaAtual não pode ser nulo.") String novaSenha) {
}