package br.unitins.topicos1.dto.reserva;

import java.time.LocalDate;

import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.Reserva;

public record ReservaResponseDTO(
        Long id,
        LocalDate dataI,
        LocalDate dataF,
        Double preco,
        Quarto quarto,
        UsuarioResponseDTO usuario) {

    public static ReservaResponseDTO valueOf(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getDataIncio(),
                reserva.getDataFim(),
                reserva.getQuarto().getPreco(),
                reserva.getQuarto(),
                UsuarioResponseDTO.valueOf(reserva.getUsuario()));
    }

}
