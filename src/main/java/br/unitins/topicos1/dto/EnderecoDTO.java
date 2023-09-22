package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;

public record EnderecoDTO(
        String estado,
        String cidade,
        String quadra,
        String rua,
        String numero) {
    public static EnderecoDTO valueOf(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero());
    }
}
