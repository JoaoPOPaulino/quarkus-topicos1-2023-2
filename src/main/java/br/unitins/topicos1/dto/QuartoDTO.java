package br.unitins.topicos1.dto;

public record QuartoDTO(
        Integer numero,
        Double preco,
        boolean disponivel,
        TipoQuartoDTO tipoQuarto) {

}
