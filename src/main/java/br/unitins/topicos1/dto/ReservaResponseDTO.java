package br.unitins.topicos1.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.topicos1.model.Reserva;

public record ReservaResponseDTO(
        Long id,
        LocalDate dataI,
        LocalDate dataF,
        Integer quantidade,
        Double preco,
        QuartoResponseDTO quarto,
        UsuarioResponseDTO usuario,
        List<PagamentoResponseDTO> pagamento) {

    public static ReservaResponseDTO valueOf(Reserva reserva) {
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getDataIncio(),
                reserva.getDataFim(),
                reserva.getQuantidade(),
                reserva.getPreco(),
                QuartoResponseDTO.valueOf(reserva.getQuarto()),
                UsuarioResponseDTO.valueOf(reserva.getUsuario()),
                reserva.getPagamento()
                        .stream()
                        .map(p -> PagamentoResponseDTO.valueOf(p)).toList());
    }

    public static List<ReservaResponseDTO> valueOf(List<Reserva> reserva) {
        return reserva.stream().map(i -> ReservaResponseDTO.valueOf(i)).toList();
    }

}
