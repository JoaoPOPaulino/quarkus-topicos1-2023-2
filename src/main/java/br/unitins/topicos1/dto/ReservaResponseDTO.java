package br.unitins.topicos1.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.topicos1.model.Reserva;

public record ReservaResponseDTO(
        LocalDate dataI,
        LocalDate dataF,
        Integer quantidade,
        Double preco,
        Long idQuarto,
        Integer numero) {

    public static ReservaResponseDTO valueOf(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getDataIncio(),
                reserva.getDataFim(),
                reserva.getQuantidade(),
                reserva.getPreco(),
                reserva.getQuarto().getId(),
                reserva.getQuarto().getNumero());
    }

    public static List<ReservaResponseDTO> valueOf(List<Reserva> reserva) {
        return reserva.stream().map(i -> ReservaResponseDTO.valueOf(i)).toList();
    }

}
