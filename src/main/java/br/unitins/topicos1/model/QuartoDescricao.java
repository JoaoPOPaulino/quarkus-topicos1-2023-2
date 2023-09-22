package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class QuartoDescricao extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_quartoHotel")
    private Quarto quartoHotel;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Quarto getQuartoHotel() {
        return quartoHotel;
    }

    public void setQuartoHotel(Quarto quartoHotel) {
        this.quartoHotel = quartoHotel;
    }

}
