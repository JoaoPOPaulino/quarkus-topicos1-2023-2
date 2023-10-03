package br.unitins.topicos1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Reserva extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private LocalDate dataInicio;
    private LocalDate dataFinal;

    @OneToOne(mappedBy = "reserva") // reserva
    private Pagamento pagamento;

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        if (dataInicio == null || dataFinal == null || dataInicio.isAfter(dataFinal)) {
            throw new IllegalArgumentException("Datas de reserva inválidas.");
        }
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        if (dataInicio == null || dataFinal == null || dataInicio.isAfter(dataFinal)) {
            throw new IllegalArgumentException("Datas de reserva inválidas.");
        }
        this.dataFinal = dataFinal;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
        if (pagamento != null) {
            pagamento.setReserva(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(quarto, reserva.quarto) &&
                Objects.equals(usuario, reserva.usuario) &&
                Objects.equals(dataInicio, reserva.dataInicio) &&
                Objects.equals(dataFinal, reserva.dataFinal) &&
                Objects.equals(pagamento, reserva.pagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quarto, usuario, dataInicio, dataFinal, pagamento);
    }
}
