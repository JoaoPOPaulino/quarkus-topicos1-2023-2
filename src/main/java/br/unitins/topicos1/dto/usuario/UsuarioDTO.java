package br.unitins.topicos1.dto.usuario;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,

        @NotBlank(message = "O e-mail é obrigatório") /*
                                                       * @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message =
                                                       * "Formato de e-mail inválido")
                                                       */String email,

        @NotBlank(message = "O login é obrigatório") String login,

        @NotBlank(message = "A senha é obrigatória") /*
                                                      * @Pattern(regexp =
                                                      * "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message =
                                                      * "A senha deve ter no mínimo 8 caracteres, incluindo uma letra maiúscula, uma letra minúscula e um número"
                                                      * )
                                                      */ String senha) {

}