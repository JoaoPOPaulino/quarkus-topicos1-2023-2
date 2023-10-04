package br.unitins.topicos1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.model.Usuario;

public class UsuarioDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O login não pode estar em branco")
    private String login;

    @NotBlank(message = "A senha não pode estar em branco")
    private String senha;

    @NotEmpty(message = "A lista de telefones não pode estar vazia")
    private List<TelefoneDTO> listaTelefone;

    @Valid
    private EnderecoDTO endereco;

    public UsuarioDTO() {

    }

    public UsuarioDTO(String nome, String login, String senha, List<TelefoneDTO> listaTelefone, EnderecoDTO endereco) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.listaTelefone = listaTelefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public List<TelefoneDTO> getListaTelefone() {
        return listaTelefone;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public static UsuarioDTO valueOf(Usuario usuario) {
        List<TelefoneDTO> telefonesDTO = usuario.getListaTelefone()
                .stream()
                .map(telefone -> TelefoneDTO.valueOf(telefone))
                .collect(Collectors.toList());

        EnderecoDTO enderecoDTO = EnderecoDTO.valueOf(usuario.getEndereco());

        return new UsuarioDTO(
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                telefonesDTO,
                enderecoDTO);
    }

}
