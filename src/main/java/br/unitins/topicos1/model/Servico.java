package br.unitins.topicos1.model;

import java.time.LocalDateTime;

import br.unitins.topicos1.dto.servico.ServicoDTO;
import jakarta.persistence.Entity;

@Entity
public class Servico extends DefaultEntity {

    private String nome;
    private String descricao;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFim;

    public Servico(ServicoDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.horaInicio = dto.horaI();
        this.horaFim = dto.horaF();
    }

    public void atualizazrServico(ServicoDTO dto) {
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.horaInicio = dto.horaI();
        this.horaFim = dto.horaF();
    }

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

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalDateTime horaFim) {
        this.horaFim = horaFim;
    }

}
