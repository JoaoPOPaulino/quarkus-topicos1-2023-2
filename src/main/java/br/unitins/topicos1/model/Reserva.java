package br.unitins.topicos1.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva extends DefaultEntity {

    private LocalDate dataIncio;
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Double preco;
    private Integer quantidade;

    public LocalDate getDataIncio() {
        return dataIncio;
    }

    public void setDataIncio(LocalDate dataIncio) {
        this.dataIncio = dataIncio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static Reserva valueOf(Long idReserva) {
        return null;
    }

    public boolean temPagamento() {
        return false;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
