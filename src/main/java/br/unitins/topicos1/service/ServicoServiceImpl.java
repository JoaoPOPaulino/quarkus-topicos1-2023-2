package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.ComentarioResponseDTO;
import br.unitins.topicos1.dto.ServicoDTO;
import br.unitins.topicos1.dto.ServicoResponseDTO;
import br.unitins.topicos1.model.Servico;
import br.unitins.topicos1.repository.ServicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ServicoServiceImpl implements ServicoService {
    @Inject
    ServicoRepository repository;

    @Override
    @Transactional
    public ServicoResponseDTO insert(@Valid ServicoDTO dto) {
        Servico novoServico = new Servico();
        novoServico.setNome(dto.nome());
        novoServico.setDescricao(dto.descricao());
        novoServico.setHoraInicio(dto.horaI());
        novoServico.setHoraFim(dto.horaF());

        repository.persist(novoServico);

        return ServicoResponseDTO.valueOf(novoServico);
    }

    @Override
    @Transactional
    public ServicoResponseDTO update(@Valid ServicoDTO dto, Long id) {
        Servico servico = repository.findById(id);
        if (servico == null) {
            throw new NotFoundException("Serviço não encontrado.");
        }

        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setHoraInicio(dto.horaI());
        servico.setHoraFim(dto.horaF());

        repository.persist(servico);
        return ServicoResponseDTO.valueOf(servico);
    }

    @Override
    public void delete(Long id) {
        Servico servico = repository.findById(id);
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Serviço não encontrado.");
        }
    }

    @Override
    public ServicoResponseDTO findById(Long id) {

        Servico servico = repository.findById(id);
        if (servico == null) {
            throw new NotFoundException("Serviço não encontrado.");
        }
        return ServicoResponseDTO.valueOf(servico);
    }

    @Override
    public List<ServicoResponseDTO> findByNome(String nome) {
        List<Servico> servicos = repository.findByNome(nome);
        List<ServicoResponseDTO> dtos = new ArrayList<>();
        for (Servico servico : servicos) {
            dtos.add(ServicoResponseDTO.valueOf(servico));
        }
        return dtos;
    }

    @Override
    public List<ServicoResponseDTO> findAll() {
        List<Servico> servicos = repository.listAll();
        return servicos.stream()
                .map(ServicoResponseDTO::valueOf)
                .toList();
    }

}
