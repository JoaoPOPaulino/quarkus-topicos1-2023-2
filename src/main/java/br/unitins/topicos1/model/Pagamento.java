package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Pagamento extends DefaultEntity {

    public enum TipoPagamento {
        CARTAO_CREDITO,
        CARTAO_DEBITO,
        DINHEIRO,
        BOLETO
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipoPagamento;

    @Column(nullable = false)
    private double valor;

    @OneToOne
    @JoinColumn(name = "reserva_id")
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
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
        if (reserva != null) {
            reserva.setPagamento(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pagamento pagamento = (Pagamento) o;
        return Double.compare(pagamento.valor, valor) == 0 &&
                tipoPagamento == pagamento.tipoPagamento &&
                Objects.equals(reserva, pagamento.reserva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoPagamento, valor, reserva);
    }
}
