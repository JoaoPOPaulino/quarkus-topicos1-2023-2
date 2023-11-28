package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O login é obrigatório") String login,
        @NotBlank(message = "A senha é obrigatório") String senha,
        Integer idPerfil,
        List<TelefoneDTO> listaTelefone,
        EnderecoDTO endereco) {

}