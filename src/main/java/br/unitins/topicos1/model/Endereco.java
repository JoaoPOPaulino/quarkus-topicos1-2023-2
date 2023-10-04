package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Endereco extends DefaultEntity {

    @NotBlank(message = "O estado não pode estar em branco")
    @Column(nullable = false, length = 50)
    private String estado;

    @NotBlank(message = "A cidade não pode estar em branco")
    @Column(nullable = false, length = 50)
    private String cidade;

    @NotBlank(message = "A quadra não pode estar em branco")
    @Column(nullable = false, length = 50)
    private String quadra;

    @NotBlank(message = "A rua não pode estar em branco")
    @Column(nullable = false, length = 50)
    private String rua;

    @NotEmpty(message = "O numero não pode estar em branco")
    @Column(nullable = false)
    private Integer numero;

    public Endereco(String estado, String cidade, String quadra, String rua, Integer numero) {
        this.estado = estado;
        this.cidade = cidade;
        this.quadra = quadra;
        this.rua = rua;
        this.numero = numero;
    }

    public Endereco() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getQuadra() {
        return quadra;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
