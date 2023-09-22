package br.unitins.topicos1.service;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.PagamentoRepository;
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
    UsuarioRepository usuarioRepository;

    @Inject
    QuartoRepository quartoRepository;

    @Inject
    PagamentoRepository pagamentoRepository;

    @Override
    @Transactional
    public ReservaResponseDTO insert(ReservaDTO dto) {
        Reserva novaReserva = new Reserva();

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId());
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        Quarto quarto = quartoRepository.findById(dto.getQuartoId());
        if (quarto == null) {
            throw new NotFoundException("Quarto não encontrado");
        }

        Pagamento pagamento = pagamentoRepository.findById(dto.getPagamentoId());
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado");
        }

        novaReserva.setUsuario(usuario);
        novaReserva.setQuarto(quarto);
        novaReserva.setDataInicio(dto.getDataInicio());
        novaReserva.setDataFinal(dto.getDataFim());
        novaReserva.setPagamento(pagamento); // Adicione aqui o relacionamento com Pagamento

        repository.persist(novaReserva);

        return ReservaResponseDTO.valueOf(novaReserva);
    }

    @Override
    @Transactional
    public ReservaResponseDTO update(ReservaDTO dto, Long id) {
        Reserva reservaExistente = repository.findById(id);
        if (reservaExistente == null) {
            throw new NotFoundException("Reserva não encontrada");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId());
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        Quarto quarto = quartoRepository.findById(dto.getQuartoId());
        if (quarto == null) {
            throw new NotFoundException("Quarto não encontrado");
        }

        Pagamento pagamento = pagamentoRepository.findById(dto.getPagamentoId());
        if (pagamento == null) {
            throw new NotFoundException("Pagamento não encontrado");
        }

        reservaExistente.setUsuario(usuario);
        reservaExistente.setQuarto(quarto);
        reservaExistente.setDataInicio(dto.getDataInicio());
        reservaExistente.setDataFinal(dto.getDataFim());
        reservaExistente.setPagamento(pagamento); // Atualize o relacionamento com Pagamento

        repository.persist(reservaExistente);

        return ReservaResponseDTO.valueOf(reservaExistente);
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
