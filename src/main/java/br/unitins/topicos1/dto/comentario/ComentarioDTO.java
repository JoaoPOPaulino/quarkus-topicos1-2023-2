package br.unitins.topicos1.dto.comentario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioDTO(
        @NotBlank(message = "O conteúdo não pode ser vazio.") String conteudo,
        LocalDateTime dataCriacao,
        @NotNull(message = "O ID do usuário é obrigatorio.") Long idUsuario) {
}
