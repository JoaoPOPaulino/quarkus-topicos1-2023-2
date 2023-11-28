package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ComentarioDTO;
import br.unitins.topicos1.dto.ComentarioResponseDTO;
import br.unitins.topicos1.model.Comentario;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.ComentarioRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ComentarioServiceImpl implements ComentarioService {
    @Inject
    ComentarioRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ComentarioResponseDTO insert(@Valid ComentarioDTO dto) {
        var usuario = usuarioRepository.findById(dto.idUsuario());
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Comentario comentario = new Comentario();
        comentario.setConteudo(dto.conteudo());
        comentario.setDataCriacao(dto.dataCriacao());
        comentario.setUsuario(usuario);

        repository.persist(comentario);
        return ComentarioResponseDTO.valueOf(comentario);

    }

    @Override
    @Transactional
    public ComentarioResponseDTO update(@Valid ComentarioDTO dto, Long id) {
        var comentario = repository.findById(id);
        if (comentario == null) {
            throw new NotFoundException("Comentário não encontrado.");
        }

        comentario.setConteudo(dto.conteudo());
        comentario.setDataCriacao(dto.dataCriacao());

        repository.persist(comentario);
        return ComentarioResponseDTO.valueOf(comentario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Comentário não encontrado.");
        }
    }

    @Override
    public ComentarioResponseDTO findById(Long id) {
        Comentario comentario = repository.findById(id);
        if (comentario == null) {
            throw new NotFoundException("Comentário não encontrado.");
        }
        return ComentarioResponseDTO.valueOf(comentario);
    }

    @Override
    public List<ComentarioResponseDTO> findAll() {
        return repository.listAll()
                .stream()
                .map(ComentarioResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}