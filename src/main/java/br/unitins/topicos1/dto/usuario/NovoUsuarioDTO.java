package br.unitins.topicos1.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record NovoUsuarioDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O e-amil é obrigatório") String email,
        @NotBlank(message = "O login é obrigatório") String login,
        @NotBlank(message = "A senha é obrigatório") String senha) {

}
