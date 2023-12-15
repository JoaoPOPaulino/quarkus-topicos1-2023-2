package br.unitins.topicos1.dto.usuario.usuarioUpdate;

import jakarta.validation.constraints.NotBlank;

public record LoginUpdateDTO(
        @NotBlank(message = "O campo login não pode ser nulo.") String login) {

}