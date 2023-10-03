package br.unitins.topicos1.service;

import java.util.List;

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

    @Transactional
    public TelefoneResponseDTO insert(TelefoneDTO dto) {
        Telefone novoTelefone = new Telefone();
        novoTelefone.setCodigoArea(dto.getCodigoArea());
        novoTelefone.setNumero(dto.getNumero());

        repository.persist(novoTelefone);

        return TelefoneResponseDTO.valueOf(novoTelefone);
    }

    @Transactional
    public TelefoneResponseDTO update(TelefoneDTO dto, Long id) {
        Telefone telefone = repository.findById(id);

        if (telefone != null) {
            telefone.setCodigoArea(dto.getCodigoArea());
            telefone.setNumero(dto.getNumero());
            return TelefoneResponseDTO.valueOf(telefone);
        } else {
            return null; // Indica que o telefone não foi encontrado
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            // Lidar com o caso em que o telefone não foi encontrado, por exemplo, lançar
            // uma exceção
            throw new RuntimeException("Telefone não encontrado");
        }
    }

    public TelefoneResponseDTO findById(Long id) {
        Telefone telefone = repository.findById(id);

        if (telefone != null) {
            return TelefoneResponseDTO.valueOf(telefone);
        } else {
            // Lidar com o caso em que o telefone não foi encontrado, por exemplo, lançar
            // uma exceção
            throw new RuntimeException("Telefone não encontrado");
        }
    }

    public List<TelefoneResponseDTO> findByAll() {
        List<Telefone> telefones = repository.listAll();
        return TelefoneResponseDTO.mapToList(telefones);
    }

}
