package br.unitins.topicos1.model;

import jakarta.persistence.Entity;

@Entity
public class Endereco extends DefaultEntity {
    
    private String estado;
    private String cidade;    
    private String quadra;
    private String rua;
    private String numero;

    public String getEstado (){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public String getCidade (){
        return cidade;
    }

    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public String getQuadra (){
        return quadra;
    }

    public void setQuadra(String quadra){
        this.quadra = quadra0;
    }

    public String getRua (){
        return rua;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public String getNumero (){
        return numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

}
