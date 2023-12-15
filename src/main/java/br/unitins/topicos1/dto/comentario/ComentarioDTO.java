package br.unitins.topicos1.dto.comentario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record ComentarioDTO(
                @NotBlank(message = "O conteúdo não pode ser vazio.") @Size(max = 500, message = "O conteúdo não pode ultrapassar 500 caracteres.") String conteudo) {
}
