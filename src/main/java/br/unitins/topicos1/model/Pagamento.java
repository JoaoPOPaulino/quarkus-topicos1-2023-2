package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento extends DefaultEntity {

    public enum TipoPagamento {
        CARTAO_CREDITO,
        CARTAO_DEBITO,
        DINHEIRO,
        TRANSFERENCIA
    }

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    private double valor;

    @OneToOne(mappedBy = "pagamento")
    private Reserva reserva;

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
