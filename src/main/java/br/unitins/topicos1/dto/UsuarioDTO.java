package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioDTO(
        @NotBlank(message = "O nome não pode estar em branco") String nome,
        @NotBlank(message = "O login não pode estar em branco") String login,
        @NotBlank(message = "A senha não pode estar em branco") String senha,
        @Valid List<TelefoneDTO> listaTelefone,
        @Valid Endereco endereco) {

    @NotEmpty(message = "A lista de telefones não pode estar vazia")
    public List<TelefoneDTO> getListaTelefone() {
        return listaTelefone;
    }
}
