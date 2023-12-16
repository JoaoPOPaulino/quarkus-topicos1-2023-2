package br.unitins.topicos1.service.usuario;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.usuario.EnderecoDTO;
import br.unitins.topicos1.dto.usuario.TelefoneDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.EmailUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.EnderecoUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.LoginUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.NomeUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.SenhaUpdateDTO;
import br.unitins.topicos1.dto.usuario.usuarioUpdate.TelefoneUpdateDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.service.hash.HashService;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;

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
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));
        novoUsuario.setPerfil(Perfil.USER);
        novoUsuario.setListaTelefone(new ArrayList<>());
        novoUsuario.setEndereco(new Endereco());

        repository.persist(novoUsuario);
        return UsuarioResponseDTO.valueOf(novoUsuario);
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
    public UsuarioResponseDTO updateNome(NomeUpdateDTO dto, String login) {
        Usuario usuario = repository.findByLogin(login);

        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        usuario.setNome(dto.nome());
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateLogin(LoginUpdateDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        if (repository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe.");
        }
        usuario.setLogin(dto.login());
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateEmail(EmailUpdateDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        usuario.setEmail(dto.email());
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateSenha(SenhaUpdateDTO dto, String login) {
        Usuario usuario = repository.findByLogin(login);

        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        if (hashService.getHashSenha(dto.senha()).equals(usuario.getSenha())) {
            usuario.setSenha(hashService.getHashSenha(dto.novaSenha()));
            repository.persist(usuario);
            return UsuarioResponseDTO.valueOf(usuario);
        } else {
            throw new ValidationException("updateSenha", "Senha antiga inválida.");
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
    @Transactional
    public UsuarioResponseDTO insertEndereco(@Valid EnderecoDTO dto, Long id) {
        Usuario usuario = repository.findById(id);

        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Endereco endereco = new Endereco();
        endereco.setEstado(dto.estado());
        endereco.setCidade(dto.cidade());
        endereco.setQuadra(dto.quadra());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());

        usuario.setEndereco(endereco);
        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateTelefone(@Valid TelefoneUpdateDTO dto, String login) {
        Usuario usuario = repository.findByLogin(login);

        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        boolean telefoneEncontrado = false;
        if (usuario.getListaTelefone() != null) {
            for (Telefone t : usuario.getListaTelefone()) {
                if (t.getId().equals(dto.id())) {
                    t.setCodigoArea(dto.codigoArea());
                    t.setNumero(dto.numero());
                    telefoneEncontrado = true;
                    break;
                }
            }
        }

        if (!telefoneEncontrado) {
            throw new NotFoundException("Telefone não encontrado.");
        }

        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateEndereco(@Valid EnderecoUpdateDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Endereco endereco = usuario.getEndereco();
        if (endereco == null) {
            throw new NotFoundException("O usuário não possui nenhum endereço cadastrado.");
        }

        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setQuadra(dto.quadra());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());

        repository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
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
