package br.unitins.topicos1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Servico extends DefaultEntity {
    
    private String nome;
    private String descricao;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public LocalDate getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalDate horaInicio) {
        this.horaInicio = horaInicio;
    }
    public LocalDate getHoraFim() {
        return horaFim;
    }
    public void setHoraFim(LocalDate horaFim) {
        this.horaFim = horaFim;
    }


    
}
