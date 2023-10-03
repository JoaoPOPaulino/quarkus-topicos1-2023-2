package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotEmpty(message = "O estado não pode estar vazio") String estado,
        @NotEmpty(message = "A cidade não pode estar vazio") String cidade,
        @NotEmpty(message = "A quadra não pode estar vazio") String quadra,
        @NotEmpty(message = "A rua não pode estar vazio") String rua,
        @NotNull(message = "O número não pode estar vazio") Integer numero) {
    public static EnderecoDTO valueOf(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero());
    }
}
