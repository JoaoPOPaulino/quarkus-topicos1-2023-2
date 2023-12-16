package br.unitins.topicos1.service.comentario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.comentario.ComentarioDTO;
import br.unitins.topicos1.dto.comentario.ComentarioResponseDTO;
import br.unitins.topicos1.dto.usuario.UsuarioDTO;
import br.unitins.topicos1.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos1.model.Comentario;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.ComentarioRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ComentarioServiceImpl implements ComentarioService {
    @Inject
    ComentarioRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ComentarioResponseDTO insert(ComentarioDTO dto, UsuarioResponseDTO usuarioLogado) {
        Usuario usuario = usuarioRepository.findById(usuarioLogado.id());
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Comentario comentario = new Comentario();
        comentario.setConteudo(dto.conteudo());
        comentario.setDataCriacao(LocalDateTime.now());
        comentario.setUsuario(usuario);
        repository.persist(comentario);
        return ComentarioResponseDTO.valueOf(comentario);
    }

    @Override
    @Transactional
    public ComentarioResponseDTO update(@Valid ComentarioDTO dto, Long id, UsuarioResponseDTO usuarioLogado) {
        var comentario = repository.findById(id);
        if (comentario == null) {
            throw new NotFoundException("Comentário não encontrado.");
        }

        // Verifica se o usuário é o autor do comentário ou se é um ADMIN
        if (!comentario.getUsuario().getId().equals(usuarioLogado.id()) && !usuarioLogado.perfil().equals("ADMIN")) {
            throw new SecurityException("Acesso negado: você não tem permissão para atualizar este comentário.");
        }

        comentario.atualizarComDto(dto);
        repository.persist(comentario);
        return ComentarioResponseDTO.valueOf(comentario);
    }

    @Override
    @Transactional
    public void delete(Long id, UsuarioResponseDTO usuarioLogado) {
        var comentario = repository.findById(id);
        if (comentario == null) {
            throw new NotFoundException("Comentário não encontrado.");
        }

        // Verifica se o usuário é o autor do comentário ou se é um ADMIN
        if (!comentario.getUsuario().getId().equals(usuarioLogado.id()) && !usuarioLogado.perfil().equals("ADMIN")) {
            throw new SecurityException("Acesso negado: você não tem permissão para excluir este comentário.");
        }

        repository.deleteById(id);
    }

    @Override
    public List<ComentarioResponseDTO> findAll() {
        return repository.listAll()
                .stream()
                .map(ComentarioResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<ComentarioResponseDTO> findComentariosByData(LocalDateTime data) {
        List<Comentario> comentarios = repository.findComentariosByData(data);
        return comentarios.stream()
                .map(ComentarioResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioResponseDTO findById(Long id) {
        Comentario comentario = repository.findById(id);
        if (comentario == null) {
            throw new NotFoundException("Comentário não encontrado.");
        }
        return ComentarioResponseDTO.valueOf(comentario);
    }
}
