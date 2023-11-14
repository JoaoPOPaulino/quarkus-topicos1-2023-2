package br.unitins.topicos1.service;

import java.time.LocalDateTime;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.model.TipoPagamento;
import br.unitins.topicos1.repository.PagamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository repository;

    @Inject
    ReservaService reservaService;

    @Override
    @Transactional
    public PagamentoResponseDTO insert(PagamentoDTO dto) {
        ReservaResponseDTO reserva = reservaService.findById(dto.idReserva());
        if (reserva == null) {
            throw new NotFoundException("Reserva não encontrada.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setValor(dto.valor());
        pagamento.setReserva(dto.idReserva());
        pagamento.setTipoPagamento(TipoPagamento.valueOf(dto.tipoPagamento().id()));
        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public PagamentoResponseDTO update(PagamentoDTO dto, Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado.");
        }

        ReservaResponseDTO reserva = reservaService.findById(dto.idReserva());
        if (reserva == null) {
            throw new NotFoundException("Reserva não encontrada.");
        }

        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setValor(dto.valor());
        pagamento.setReserva(dto.idReserva());
        pagamento.setTipoPagamento(TipoPagamento.valueOf(dto.tipoPagamento().id()));

        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado.");
        }
        repository.delete(pagamento);
    }

    @Override
    public PagamentoResponseDTO findById(Long id) {
        Pagamento pagamento = repository.findById(id);
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado.");
        }
        return PagamentoResponseDTO.valueOf(pagamento);
    }
}
