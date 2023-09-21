package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.TipoQuarto;

public record QuartoResponseDTO(
        Long id,
        int numero,
        TipoQuarto tipo,
        double preco,
        boolean disponivel) {

    public static QuartoResponseDTO valueOf(Quarto quarto) {
        return new QuartoResponseDTO(
                quarto.getId(),
                quarto.getNumero(),
                quarto.getTipo(),
                quarto.getPreco(),
                quarto.getDisponivel());
    }
}
