package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.model.MetodoPagamento;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.repository.PagamentoRepository;
import br.unitins.topicos1.repository.ReservaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    @Inject
    PagamentoRepository repository;

    @Inject
    ReservaRepository reservaRepository; // para verificar a existência da reserva

    @Override
    @Transactional
    public PagamentoResponseDTO insert(PagamentoDTO dto) {
        Pagamento pagamento = new Pagamento();

        Reserva reserva = reservaRepository.findById(dto.getReservaId());
        if (reserva == null) {
            throw new NotFoundException("Reserva não encontrada!");
        }

        repository.persist(pagamento);
        return PagamentoResponseDTO.valueOf(pagamento);
    }

}
