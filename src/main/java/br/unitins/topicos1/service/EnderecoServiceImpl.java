package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository repository;

    @Override
    @Transactional
    public EnderecoResponseDTO insert(EnderecoDTO dto) {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setEstado(dto.getEstado());
        novoEndereco.setCidade(dto.getCidade());
        novoEndereco.setQuadra(dto.getQuadra());
        novoEndereco.setRua(dto.getRua());
        novoEndereco.setNumero(dto.getNumero());

        repository.persist(novoEndereco);

        return EnderecoResponseDTO.valueOf(novoEndereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(EnderecoDTO dto, Long id) {
        Endereco endereco = repository.findById(id);

        if (endereco != null) {
            endereco.setEstado(dto.getEstado());
            endereco.setCidade(dto.getCidade());
            endereco.setQuadra(dto.getQuadra());
            endereco.setRua(dto.getRua());
            endereco.setNumero(dto.getNumero());
        } else {
            throw new NotFoundException();
        }

        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        Endereco endereco = repository.findById(id);

        if (endereco != null) {
            return EnderecoResponseDTO.valueOf(endereco);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<EnderecoResponseDTO> findAll() {
        List<Endereco> enderecos = repository.listAll();
        return EnderecoResponseDTO.mapToList(enderecos);
    }
}
