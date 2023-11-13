package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.TelefoneRepository;
import br.unitins.topicos1.repository.UsuarioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService {

    @Inject
    TelefoneRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public TelefoneResponseDTO insert(TelefoneDTO dto, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Telefone telefone = new Telefone();
        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());
        telefone.setUsuario(usuario);

        repository.persist(telefone);

        return TelefoneResponseDTO.valueOf(telefone);
    }

    @Override
    @Transactional
    public TelefoneResponseDTO update(TelefoneDTO dto, Long telefoneId) {
        Telefone telefone = repository.findById(telefoneId);
        if (telefone == null) {
            throw new NotFoundException("Telefone não encontrado.");
        }

        telefone.setCodigoArea(dto.codigoArea());
        telefone.setNumero(dto.numero());

        return TelefoneResponseDTO.valueOf(telefone);
    }

    @Override
    @Transactional
    public void delete(Long telefoneId) {
        if (!repository.deleteById(telefoneId)) {
            throw new NotFoundException("Telefone não encontrado.");
        }
    }

    @Override
    public TelefoneResponseDTO findById(Long telefoneId) {
        Telefone telefone = repository.findById(telefoneId);
        if (telefone == null) {
            throw new NotFoundException("Telefone não encontrado.");
        }
        return TelefoneResponseDTO.valueOf(telefone);
    }

    @Override
    public List<TelefoneResponseDTO> findByUser(Long usuarioId) {
        List<Telefone> telefones = repository.findByusuarioId(usuarioId);
        return telefones.stream()
                .map(TelefoneResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
