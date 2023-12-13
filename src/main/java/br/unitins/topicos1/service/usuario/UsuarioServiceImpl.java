package br.unitins.topicos1.service.usuario;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.Telefone.TelefoneDTO;
import br.unitins.topicos1.dto.Telefone.TelefoneUpdateDTO;
import br.unitins.topicos1.dto.usuario.NovoUsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.resource.UsuarioResource;
import br.unitins.topicos1.service.hash.HashService;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) {
        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }
        Usuario novoUsuario = new Usuario(dto, hashService);
        repository.persist(novoUsuario);
        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(@Valid UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        Usuario usuarioExistente = repository.findByLogin(dto.login());
        if (usuarioExistente != null && !usuarioExistente.getId().equals(id)) {
            throw new ValidationException("login", "Login já existe.");
        }
        usuario.atualizarComDTO(dto, hashService);
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
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

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = repository.findByLoginAndSenha(login, senha);
        if (usuario == null)
            throw new ValidationException("login", "Login ou senha inválido");

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario usuario = repository.findByLogin(login);
        if (usuario == null) {
            throw new ValidationException("login", "Login não encontrado");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateNome(UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        usuario.setNome(dto.nome());
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateLogin(UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }
        usuario.setLogin(dto.login());
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateEmail(UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        usuario.setEmail(dto.email());
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateSenha(UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);

        if (hashService.getHashSenha(dto.senha()).equals(usuario.getSenha())) {
            usuario.setSenha(hashService.getHashSenha(dto.senha()));
            return UsuarioResponseDTO.valueOf(usuario);
        } else {
            throw new ValidationException("updateSenha", "Favor inserir a senha antiga correta.");
        }
    }

    @Override
    @Transactional
    public UsuarioResponseDTO insertTelefone(@Valid TelefoneDTO dto, Long id) {
        Usuario usuario = repository.findById(id);

        Telefone telefone = new Telefone();
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        usuario.getListaTelefone().add(telefone);

        repository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO updateTelefone(@Valid TelefoneUpdateDTO dto, Long id) {
        Usuario usuario = repository.findById(id);

        if (usuario.getListaTelefone() != null || !usuario.getListaTelefone().isEmpty()) {
            for (Telefone t : usuario.getListaTelefone()) {
                if (t.getId() == dto.id()) {
                    t.setCodigoArea(dto.codigoArea());
                    t.setNumero(dto.numero());
                }
            }
        } else {
            throw new NotFoundException("O usuário não possui nenhum telefone cadastrado.");
        }

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO updateEndereco(@Valid EnderecoDTO dto, Long id) {
        Usuario usuario = repository.findById(id);

        if (usuario.getEndereco() != null) {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setCidade(dto.cidade());
            novoEndereco.setEstado(dto.estado());
            novoEndereco.setQuadra(dto.quadra());
            novoEndereco.setRua(dto.rua());
            novoEndereco.setNumero(dto.numero());
        } else {
            throw new NotFoundException("O usuário não possui nenhum endereço cadastrado.");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public NovoUsuarioDTO insertNovo(@Valid NovoUsuarioDTO dto) {
        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setLogin(dto.login());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(dto.senha());
        novoUsuario.setPerfil(Perfil.USER);
        repository.persist(novoUsuario);
        return NovoUsuarioDTO.valueOf(novoUsuario);
    }

    @Override
    public UsuarioResponseDTO updatePerfil(long id, Integer perfilId) {
        Usuario usuario = repository.findById(id);

        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Perfil novoPerfil = Perfil.valueOf(perfilId);
        usuario.setPerfil(novoPerfil);
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

}
