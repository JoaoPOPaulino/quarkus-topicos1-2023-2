package br.unitins.topicos1.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Quarto extends DefaultEntity {

    private TipoQuarto tipoQuarto;
    private Integer numero;
    private Double preco;
    private boolean disponivel;

    private String nomeImagem;

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public Integer getNumero() {
        return numero;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setNumero(Integer numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("O número do quarto deve ser positivo.");
        }
        this.numero = numero;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço do quarto deve ser positivo.");
        }
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

}
