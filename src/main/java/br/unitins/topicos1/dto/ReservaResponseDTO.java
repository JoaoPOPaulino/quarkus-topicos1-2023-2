package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Reserva;

public record ReservaResponseDTO(
        Long id,
        Long usuarioId,
        Long quartoId,
        Long pagamentoId,
        LocalDate dataInicio,
        LocalDate dataFim) {

    public static ReservaResponseDTO valueOf(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getUsuario().getId(),
                reserva.getQuarto().getId(),
                reserva.getPagamento().getId(),
                reserva.getDataInicio(),
                reserva.getDataFinal());
    }
}
