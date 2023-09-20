package br.unitins.topicos1.model;

import java.time.LocalDate;

@Entity
public class Reserva extends DefaultEntity{
  
    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private QuartoHotel quarto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private Double precoTotal;

    
    public QuartoHotel getQuarto(){
        return quarto;
    }
    public void setQuarto(QuartoHotel quarto){
        this.quarto = quarto;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public LocalDate getDataInicio(){
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio){
        this.dataInicio = dataInicio;
    }
    
    public LocalDate getDataFinal(){
        return dataFinal;
    }
    public void setDataFinal(LocalDate dataFinal ){
        this.dataFinal = dataFinal;
    }
    
    public Double getPrecoTotal(){
        return precoTotal;
    }
    public void setPrecoTotal(Double precoTotal){
        this.precoTotal = precoTotal;
    }

}