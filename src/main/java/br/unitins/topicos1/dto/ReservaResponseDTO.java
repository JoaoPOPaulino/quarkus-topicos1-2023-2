package br.unitins.topicos1.dto;

import java.time.LocalDate;

public record ReservaDTO(
    Long id,
    Long usuarioId,
    Long quartoId, 
    LocalDate dataInicio,
    LocalDate dataFim,
    Double precoTotal) {

    public static ReservaResponseDTO valueOf(Reserva reserva) {
        return new ReservaResponseDTO ();
                reserva.getId();
                reserva.getUsuarioId();
                reserva.getQuartoId();
                reserva.getDataInicio();
                reserva.getDataFim();
                reserva.getPrecoTotal();
    }
}
