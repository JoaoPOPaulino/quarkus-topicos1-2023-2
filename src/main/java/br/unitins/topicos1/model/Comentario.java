package br.unitins.topicos1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Comentario extends DefaultEntity {

    
    private String conteudo;
    private LocalDateTime dataCriacao;
    @OneToMany
    @JoinColumn(name = "id_usuarios")
    private Usuario usuario;

	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
}



  
	
}