package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.QuartoHotel;

public record QuartoHotelResponseDTO(
        Long id,
        int numero,
        String tipo,
        double preco,
        boolean disponivel) {

    public static QuartoHotelResponseDTO valueOf(QuartoHotel quartoHotel) {
        return new QuartoHotelResponseDTO(
                quartoHotel.getId(),
                quartoHotel.getNumero(),
                quartoHotel.getTipo(),
                quartoHotel.getPreco(),
                quartoHotel.getDisponivel());
    }
}
