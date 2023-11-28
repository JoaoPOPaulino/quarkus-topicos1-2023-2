package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.QuartoDTO;
import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.repository.QuartoRepository;
import br.unitins.topicos1.repository.ReservaRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
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

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ReservaResponseDTO insert(ReservaDTO dto) {

        Quarto quarto = quartoRepository.findById(dto.idQuarto());
        if (quarto == null) {
            throw new IllegalArgumentException("Quarto não encontrado");
        }

        Reserva novaReserva = new Reserva();
        novaReserva.setQuarto(quarto);
        novaReserva.setDataIncio(dto.dataI());
        novaReserva.setDataFim(dto.dateF());
        novaReserva.setQuantidade(dto.quantidade());
        novaReserva.setPreco(dto.preco());

        novaReserva.setUsuario(usuarioRepository.findById(dto.idUsuario()));
        if (usuarioRepository == null) {
            throw new NotFoundException("Usuário não encontrado");
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

        Quarto quarto = quartoRepository.findById(id);
        if (quarto == null) {
            throw new NotFoundException("Quarto não encontrado");
        }

        reserva.setUsuario(usuarioRepository.findById(id));
        if (reserva.getUsuario() == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

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
    public List<ReservaResponseDTO> findByAll() {
        List<Reserva> reservas = repository.listAll();
        return reservas.stream()
                .map(ReservaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Transactional
    public void atualizarReservaComPagamento(Long idReserva, Pagamento pagamento) {
        Reserva reserva = repository.findById(idReserva);
        if (reserva == null) {
            throw new NotFoundException("Reserva não encontrada.");
        }
    }

    @Override
    public ReservaResponseDTO findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<ReservaResponseDTO> findReservaByUsuarioId(Long usuarioId) {
        List<Reserva> reservas = repository.findByUsuario(usuarioId);
        return reservas.stream()
                .map(ReservaResponseDTO::valueOf)
                .collect(Collectors.toList()); // TODO Auto-generated method stub
    }
}
