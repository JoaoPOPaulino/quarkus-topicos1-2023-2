package br.unitins.topicos1.service.usuario;

import java.util.List;

import br.unitins.topicos1.dto.Telefone.TelefoneDTO;
import br.unitins.topicos1.dto.Telefone.TelefoneUpdateDTO;
import br.unitins.topicos1.dto.email.EmailUpdateDTO;
import br.unitins.topicos1.dto.endereco.EnderecoUpdateDTO;
import br.unitins.topicos1.dto.login.LoginUpdateDTO;
import br.unitins.topicos1.dto.login.SenhaUpdateDTO;
import br.unitins.topicos1.dto.nome.NomeUpdateDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    UsuarioResponseDTO insert(@Valid UsuarioDTO dto);

    UsuarioResponseDTO update(@Valid UsuarioDTO dto, Long id);

    void delete(Long id);

    UsuarioResponseDTO findById(Long id);

    List<UsuarioResponseDTO> findByNome(String nome);

    List<UsuarioResponseDTO> findByAll();

    UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    UsuarioResponseDTO findByLogin(String login);

    UsuarioResponseDTO updateNome(@Valid NomeUpdateDTO dto, Long id);

    UsuarioResponseDTO updateLogin(@Valid LoginUpdateDTO dto, Long id);

    UsuarioResponseDTO updateEmail(@Valid EmailUpdateDTO dto, Long id);

    UsuarioResponseDTO updateSenha(@Valid SenhaUpdateDTO dto, Long id);

    UsuarioResponseDTO insertTelefone(@Valid TelefoneDTO dto, Long id);

    UsuarioResponseDTO updateTelefone(@Valid TelefoneUpdateDTO dto, Long id);

    UsuarioResponseDTO updateEndereco(@Valid EnderecoUpdateDTO dto, Long id);

    UsuarioResponseDTO updatePerfil(long id, Integer perfil);

}
