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
    private QuartoHotel quartoHotel;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public QuartoHotel getQuartoHotel() {
        return quartoHotel;
    }

    public void setQuartoHotel(QuartoHotel quartoHotel) {
        this.quartoHotel = quartoHotel;
    }

}
