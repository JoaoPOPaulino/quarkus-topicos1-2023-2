package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;
import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.model.Telefone;
import br.unitins.topicos1.repository.TelefoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService {

    @Inject
    TelefoneRepository repository;

    @Override
    @Transactional
    public TelefoneResponseDTO insert(TelefoneDTO dto) {
        Telefone novoTelefone = new Telefone();
        novoTelefone.setCodigoArea(dto.codigoArea());
        novoTelefone.setNumero(dto.numero());

        repository.persist(novoTelefone);

        return TelefoneResponseDTO.valueOf(novoTelefone);
    }

    @Override
    @Transactional
    public TelefoneResponseDTO update(TelefoneDTO dto, Long id) {
        Telefone telefoneExistente = repository.findById(id);
        if (telefoneExistente == null) {
            return null; // Ou lançar uma exceção
        }

        telefoneExistente.setCodigoArea(dto.codigoArea());
        telefoneExistente.setNumero(dto.numero());

        repository.persist(telefoneExistente);

        return TelefoneResponseDTO.valueOf(telefoneExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TelefoneResponseDTO findById(Long id) {
        Telefone telefone = repository.findById(id);
        if (telefone == null) {
            return null; // Ou lançar uma exceção
        }
        return TelefoneResponseDTO.valueOf(telefone);
    }

    @Override
    public List<TelefoneResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(TelefoneResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
