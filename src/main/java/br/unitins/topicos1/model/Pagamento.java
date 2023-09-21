package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento extends DefaultEntity {

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento; // ENUM

    private double valor;

    @OneToOne
    private Reserva reservaId;

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Reserva getReservaId() {
        return reservaId;
    }

    public void setReservaId(Reserva reservaId) {
        this.reservaId = reservaId;
    }

}
