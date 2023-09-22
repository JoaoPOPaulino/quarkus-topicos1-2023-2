package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Reserva;

public record ReservaResponseDTO(
        Long id,
        Long usuarioId,
        Long quartoId,
        LocalDate dataInicio,
        LocalDate dataFim,
        Double precoTotal) {

    public static ReservaResponseDTO valueOf(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getUsuario().getId(), // Supondo que getUsuario() retorne um objeto Usuario com um método
                                              // getId()
                reserva.getQuarto().getId(), // Supondo que getQuarto() retorne um objeto QuartoHotel com um método
                                             // getId()
                reserva.getDataInicio(),
                reserva.getDataFinal(), // Verifique o nome correto desse método na sua classe Reserva
                reserva.getPrecoTotal());
    }

}
