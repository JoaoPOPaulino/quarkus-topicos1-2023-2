package br.unitins.topicos1.dto;

import java.util.List;

import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioResponseDTO(
                @NotEmpty(message = "O ID não pode estar vazio") Long id,
                @NotBlank(message = "O nome não pode estar em branco") String nome,
                @NotBlank(message = "O login não pode estar em branco") String login,
                @Valid List<TelefoneDTO> listaTelefone,
                @Valid EnderecoDTO endereco) {

        public static UsuarioResponseDTO valueOf(Usuario usuario) {
                List<TelefoneDTO> telefonesDTO = usuario.getListaTelefone()
                                .stream()
                                .map(telefone -> TelefoneDTO.valueOf(telefone))
                                .toList();

                EnderecoDTO enderecoDTO = EnderecoDTO.valueOf(usuario.getEndereco());
                return new UsuarioResponseDTO(
                                usuario.getId(),
                                usuario.getNome(),
                                usuario.getLogin(),
                                telefonesDTO,
                                enderecoDTO);
        }

        public Long getId() {
                return id;
        }
}
