package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Quarto extends DefaultEntity {

    public enum TipoQuarto {
        SIMPLES,
        CASAL,
        LUXO,
        PRESIDENCIAL
    }

    @Column(length = 3)
    private int numero;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private TipoQuarto tipo;

    @Column(length = 5)
    private double preco;

    @Column(length = 10)
    private Boolean disponivel;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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
        this.preco = preco;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
