package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.repository.QuartoRepository;
import br.unitins.topicos1.repository.ReservaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ReservaServiceImpl implements ReservaService {

    @Inject
    ReservaRepository repository;

    @Inject
    QuartoRepository quartoRepository;

    @Override
    @Transactional
    public ReservaResponseDTO insert(ReservaDTO dto) {
        Reserva novaReserva = new Reserva();
        novaReserva.setDataIncio(dto.dataI());
        novaReserva.setDataFim(dto.dateF());
        novaReserva.setQuantidade(dto.quantidade());
        novaReserva.setQuarto(quartoRepository.findById(dto.idQuarto()));
        if (quartoRepository == null) {
            throw new NotFoundException("Quarto não encontrado");
        }

        repository.persist(novaReserva);
        return ReservaResponseDTO.valueOf(novaReserva);
    }

    @Override
    @Transactional
    public ReservaResponseDTO update(ReservaDTO dto, Long id) {
        Reserva reserva = repository.findById(id);
        if (reserva == null) {
            throw new NotFoundException("Reserva não encontrada");
        }

        reserva.setDataIncio(dto.dataI());
        reserva.setDataFim(dto.dateF());
        reserva.setPreco(dto.preco());
        reserva.setQuantidade(dto.quantidade());

        repository.persist(reserva);

        return ReservaResponseDTO.valueOf(reserva);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Reserva reserva = repository.findById(id);
        if (reserva == null) {
            throw new NotFoundException("Reserva não encontrada");
        }
        repository.delete(reserva);
    }

    @Override
    public ReservaResponseDTO findById(Long id) {
        Reserva reserva = repository.findById(id);
        if (reserva == null) {
            throw new NotFoundException("Reserva não encontrada");
        }
        return ReservaResponseDTO.valueOf(reserva);
    }

    @Override
    public List<ReservaResponseDTO> findByAll() {
        List<Reserva> reservas = repository.listAll();
        return reservas.stream()
                .map(ReservaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
