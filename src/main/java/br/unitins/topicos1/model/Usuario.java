package br.unitins.topicos1.model;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.service.hash.HashService;
import jakarta.persistence.FetchType;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Usuario extends DefaultEntity {

    private String nome;
    private String login;
    private String senha;
    private Perfil perfil;

    @NotEmpty(message = "A lista de telefone não pode estar vazia")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_telefone", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_telefone"))
    private List<Telefone> listaTelefone;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_endereco", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_endereco"))
    private Endereco endereco;

    public Usuario(UsuarioDTO dto, HashService hashService) {
        this.nome = dto.nome();
        this.login = dto.login();
        this.senha = hashService.getHashSenha(dto.senha());
        this.perfil = Perfil.valueOf(dto.idPerfil());

        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            this.listaTelefone = new ArrayList<>();
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                this.listaTelefone.add(telefone);
            }
        }

        if (dto.endereco() != null) {
            EnderecoDTO enderecoDTO = dto.endereco();
            this.endereco = new Endereco();
            this.endereco.setEstado(enderecoDTO.estado());
            this.endereco.setCidade(enderecoDTO.cidade());
            this.endereco.setQuadra(enderecoDTO.quadra());
            this.endereco.setRua(enderecoDTO.rua());
            this.endereco.setNumero(enderecoDTO.numero());
        }
    }

    public void atualizarComDTO(UsuarioDTO dto, HashService hashService) {
        this.nome = dto.nome();
        this.login = dto.login();
        if (dto.senha() != null && !dto.senha().isEmpty()) {
            this.senha = hashService.getHashSenha(dto.senha());
        }
        this.perfil = Perfil.valueOf(dto.idPerfil());

        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            this.listaTelefone = new ArrayList<>();
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                this.listaTelefone.add(telefone);
            }
        }

        if (dto.endereco() != null) {
            EnderecoDTO enderecoDTO = dto.endereco();
            this.endereco = new Endereco();
            this.endereco.setEstado(enderecoDTO.estado());
            this.endereco.setCidade(enderecoDTO.cidade());
            this.endereco.setQuadra(enderecoDTO.quadra());
            this.endereco.setRua(enderecoDTO.rua());
            this.endereco.setNumero(enderecoDTO.numero());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Telefone> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<Telefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
