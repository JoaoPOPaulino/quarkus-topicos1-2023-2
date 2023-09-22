package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.repository.PagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository repository;

    @Override
    @Transactional
    public PagamentoResponseDTO insert(PagamentoDTO dto) {
        Pagamento novoPagamento = new Pagamento();
        novoPagamento.setTipoPagamento(dto.getTipoPagamento());
        // Adicione outros campos aqui, se necessário

        repository.persist(novoPagamento);

        return PagamentoResponseDTO.valueOf(novoPagamento);
    }

    @Override
    @Transactional
    public PagamentoResponseDTO update(PagamentoDTO dto, Long id) {
        Pagamento pagamentoExistente = repository.findById(id);
        if (pagamentoExistente == null) {
            throw new NotFoundException("Pagamento não encontrado");
        }

        pagamentoExistente.setTipoPagamento(dto.getTipoPagamento());
        // Atualize outros campos aqui, se necessário

        repository.persist(pagamentoExistente);

        return PagamentoResponseDTO.valueOf(pagamentoExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado");
        }
        repository.delete(pagamento);
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado");
        }
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    public List<PagamentoResponseDTO> findByAll() {
        List<Pagamento> pagamentos = repository.listAll();
        return pagamentos.stream()
                .map(PagamentoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

}
