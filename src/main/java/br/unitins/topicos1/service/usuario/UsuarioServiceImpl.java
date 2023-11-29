package br.unitins.topicos1.service.usuario;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Perfil;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.UsuarioRepository;
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

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));
        novoUsuario.setPerfil(Perfil.valueOf(dto.idPerfil()));

        if (dto.listaTelefone() != null &&
                !dto.listaTelefone().isEmpty()) {
            novoUsuario.setListaTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                novoUsuario.getListaTelefone().add(telefone);
            }
        }

        if (dto.endereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setEstado(dto.endereco().estado());
            endereco.setCidade(dto.endereco().cidade());
            endereco.setQuadra(dto.endereco().quadra());
            endereco.setRua(dto.endereco().rua());
            endereco.setNumero(dto.endereco().numero());

            novoUsuario.setEndereco(endereco);

        }

        repository.persist(novoUsuario);
        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        usuario.setNome(dto.nome());
        usuario.setLogin(dto.login());

        usuario.setSenha(hashService.getHashSenha(dto.senha()));

        usuario.getListaTelefone().clear();
        for (TelefoneDTO tel : dto.listaTelefone()) {
            Telefone telefone = new Telefone();
            telefone.setCodigoArea(tel.codigoArea());
            telefone.setNumero(tel.numero());
            usuario.getListaTelefone().add(telefone);
        }

        if (dto.endereco() != null && dto.endereco() == null) {
            Endereco endereco = new Endereco();
            endereco.setEstado(dto.endereco().estado());
            endereco.setCidade(dto.endereco().cidade());
            endereco.setQuadra(dto.endereco().quadra());
            endereco.setRua(dto.endereco().rua());
            endereco.setNumero(dto.endereco().numero());
            usuario.setEndereco(endereco);
        }

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
}
