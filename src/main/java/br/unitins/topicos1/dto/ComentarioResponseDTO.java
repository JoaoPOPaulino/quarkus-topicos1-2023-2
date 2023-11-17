package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import br.unitins.topicos1.model.Comentario;
import br.unitins.topicos1.model.Usuario;

public record ComentarioResponseDTO(
        Long id,
        String conteudo,
        LocalDateTime dataCriacao,
        Usuario usuario) {
    public static ComentarioResponseDTO valueOf(Comentario comentario) {
        return new ComentarioResponseDTO(
                comentario.getId(),
                comentario.getConteudo(),
                comentario.getDataCriacao(),
                comentario.getUsuario());
    }
}
