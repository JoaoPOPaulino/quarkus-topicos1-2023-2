package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ComentarioDTO(
        @NotBlank(message = "O conteúdo não pode ser vazio.") String conteudo,
        @NotNull(message = "A data não pode ser nula.") LocalDateTime dataPublicacao,
        @NotNull(message = "O ID do usuário não pode ser nulo.") Long idUsuario) {
}
