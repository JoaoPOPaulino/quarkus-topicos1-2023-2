package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Quarto;

public record QuartoDTO(
                Integer numero,
                Double preco,
                boolean disponivel,
                TipoQuartoDTO tipoQuarto) {

}
