package br.unitins.topicos1.service.usuario;

import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.Telefone.TelefoneDTO;
import br.unitins.topicos1.dto.Telefone.TelefoneUpdateDTO;
import br.unitins.topicos1.dto.usuario.NovoUsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    UsuarioResponseDTO insert(@Valid UsuarioDTO dto);

    UsuarioResponseDTO insertNovo(@Valid NovoUsuarioDTO dto);

    UsuarioResponseDTO update(@Valid UsuarioDTO dto, Long id);

    void delete(Long id);

    UsuarioResponseDTO findById(Long id);

    List<UsuarioResponseDTO> findByNome(String nome);

    List<UsuarioResponseDTO> findByAll();

    UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    UsuarioResponseDTO findByLogin(String login);

    UsuarioResponseDTO updateNome(@Valid UsuarioDTO dto, Long id);

    UsuarioResponseDTO updateLogin(@Valid UsuarioDTO dto, Long id);

    UsuarioResponseDTO updateEmail(@Valid UsuarioDTO dto, Long id);

    UsuarioResponseDTO updateSenha(@Valid UsuarioDTO dto, Long id);

    UsuarioResponseDTO insertTelefone(@Valid TelefoneDTO dto, Long id);

    UsuarioResponseDTO updateTelefone(@Valid TelefoneUpdateDTO dto, Long id);

    UsuarioResponseDTO updateEndereco(@Valid EnderecoDTO dto, Long id);

    UsuarioResponseDTO updatePerfil(long id, Integer perfil);

}
