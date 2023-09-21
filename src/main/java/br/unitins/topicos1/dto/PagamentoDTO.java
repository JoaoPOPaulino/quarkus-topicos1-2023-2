package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.MetodoPagamento;
import br.unitins.topicos1.model.Reserva;

public class PagamentoDTO {
    private final MetodoPagamento metodoPagamento;
    private final double valor;
    private final Reserva reservaId;

    public PagamentoDTO(MetodoPagamento metodoPagamento, double valor, Reserva reservaId) {
        this.metodoPagamento = metodoPagamento;
        this.valor = valor;
        this.reservaId = reservaId;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public double getValor() {
        return valor;
    }

    public Long getReservaId() {
        return reservaId;
    }

}
