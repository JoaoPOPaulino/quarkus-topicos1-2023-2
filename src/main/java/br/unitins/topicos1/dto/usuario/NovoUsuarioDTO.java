package br.unitins.topicos1.dto.usuario;

import br.unitins.topicos1.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record NovoUsuarioDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,

        @NotBlank(message = "O e-mail é obrigatório") String email, // @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",
                                                                    // message = "Formato de e-mail inválido")

        @NotBlank(message = "O login é obrigatório") String login,

        // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message
        // = "A senha deve ter no mínimo 8 caracteres, incluindo uma letra maiúscula,
        // uma letra minúscula e um número")

        @NotBlank(message = "A senha é obrigatória") String senha) {
    public static NovoUsuarioDTO valueOf(Usuario usuario) {
        return new NovoUsuarioDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha());
    }
}