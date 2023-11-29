package br.unitins.topicos1.dto.quarto;

import br.unitins.topicos1.dto.tipo.TipoQuartoDTO;

public record QuartoDTO(
                Integer numero,
                Double preco,
                boolean disponivel,
                TipoQuartoDTO tipoQuarto) {

}
