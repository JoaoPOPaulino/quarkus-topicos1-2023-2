package br.unitins.topicos1.dto.tipo;

import br.unitins.topicos1.model.TipoQuarto;

public record TipoQuartoDTO(
        Integer id,
        String label) {

    public static TipoQuartoDTO valueOf(TipoQuarto tipoQuarto2) {
        if (tipoQuarto2 == null) {
            return null;
        }

        for (TipoQuarto tipoQuarto : TipoQuarto.values()) {
            if (tipoQuarto.getId().equals(tipoQuarto2)) {
                return new TipoQuartoDTO(tipoQuarto.getId(),
                        tipoQuarto.getLabel());
            }
        }

        throw new IllegalArgumentException("Id inválido: " + tipoQuarto2);
    }
}
