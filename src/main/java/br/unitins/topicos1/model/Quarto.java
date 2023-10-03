package br.unitins.topicos1.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Quarto extends DefaultEntity {

    public enum TipoQuarto {
        SIMPLES,
        CASAL,
        LUXO,
        PRESIDENCIAL
    }

    @Column(nullable = false)
    private int numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQuarto tipo;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private boolean disponivel;

    @ManyToOne
    @JoinColumn(name = "quarto_descricao_id")
    private QuartoDescricao quartoDescricao;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("O número do quarto deve ser positivo.");
        }
        this.numero = numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public void setTipo(TipoQuarto tipo) {
        this.tipo = tipo;
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

    public QuartoDescricao getQuartoDescricao() {
        return quartoDescricao;
    }

    public void setQuartoDescricao(QuartoDescricao quartoDescricao) {
        this.quartoDescricao = quartoDescricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Quarto quarto = (Quarto) o;
        return numero == quarto.numero &&
                tipo == quarto.tipo &&
                Objects.equals(preco, quarto.preco) &&
                disponivel == quarto.disponivel; // Correção aqui
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, tipo, preco, disponivel); // Correção aqui
    }

}
