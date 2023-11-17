package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record ComentarioDTO(
    @NotNull(message = "O Comentário não pode ter espaços vazios, comente algo!!.")String conteudo,
    @NotNull(message = "A data do cometário não deve ser nula") LocalDateTime dataPublicacao,
    @NotNull(message = "Não é possível criar um comentário sem o usuario")  Long idUsuario
) {
    

}
