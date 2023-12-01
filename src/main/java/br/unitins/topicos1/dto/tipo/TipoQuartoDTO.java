package br.unitins.topicos1.dto.tipo;

import br.unitins.topicos1.model.TipoQuarto;

public record TipoQuartoDTO(
        Integer id,
        String label) {

    public static TipoQuartoDTO valueOf(TipoQuarto tipoQuarto) {
        return new TipoQuartoDTO(tipoQuarto.getId(), tipoQuarto.getLabel());
    }

}
