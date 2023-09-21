package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Endereco;

public record UsuarioDTO(
                String nome,
                String login,
                String senha,
                List<TelefoneDTO> listaTelefone,
                Endereco endereco) {

}
