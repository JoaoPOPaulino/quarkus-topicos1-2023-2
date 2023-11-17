package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.model.Usuario;

public record ReservaResponseDTO(
        Long id,
        LocalDate dataI,
        LocalDate dataF,
        Integer quantidade,
        Double quarto,
        Quarto preco,
        Usuario usuario) {

    public static ReservaResponseDTO valueOf(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getDataIncio(),
                reserva.getDataFim(),
                reserva.getQuantidade(),
                reserva.getQuarto().getPreco(),
                reserva.getQuarto(),
                reserva.getUsuario());
    }

    public Reserva reserva() {
        return reserva();
    }
}
