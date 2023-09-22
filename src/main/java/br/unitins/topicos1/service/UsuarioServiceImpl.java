package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.TelefoneRepository;
import br.unitins.topicos1.repository.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(UsuarioDTO dto) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(dto.senha());

        if (dto.listaTelefone() != null && !dto.listaTelefone().isEmpty()) {
            novoUsuario.setListaTelefone(new ArrayList<>());
            for (TelefoneDTO telDto : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(telDto.codigoArea());
                telefone.setNumero(telDto.numero());
                novoUsuario.getListaTelefone().add(telefone);
            }
        }

        repository.persist(novoUsuario);
        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {
        Usuario usuarioExistente = repository.findById(id);
        if (usuarioExistente == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        usuarioExistente.setNome(dto.nome());
        usuarioExistente.setLogin(dto.login());
        usuarioExistente.setSenha(dto.senha());

        // Atualizar a lista de telefones
        usuarioExistente.getListaTelefone().clear();
        for (TelefoneDTO telDto : dto.listaTelefone()) {
            Telefone telefone = new Telefone();
            telefone.setCodigoArea(telDto.codigoArea());
            telefone.setNumero(telDto.numero());
            usuarioExistente.getListaTelefone().add(telefone);
        }

        repository.persist(usuarioExistente);

        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Usuário não encontrado.");
        }
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        List<Usuario> usuarios = repository.findByNome(nome);
        List<UsuarioResponseDTO> dtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            dtos.add(UsuarioResponseDTO.valueOf(usuario));
        }
        return dtos;
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        List<Usuario> usuarios = repository.listAll();
        List<UsuarioResponseDTO> dtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            dtos.add(UsuarioResponseDTO.valueOf(usuario));
        }
        return dtos;
    }
}
