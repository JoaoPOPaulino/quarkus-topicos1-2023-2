package br.unitins.topicos1.service.reserva;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.dto.quarto.QuartoDTO;
import br.unitins.topicos1.dto.reserva.ReservaDTO;
import br.unitins.topicos1.dto.reserva.ReservaResponseDTO;
import br.unitins.topicos1.model.Pagamento;
import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.model.Usuario;
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
        Usuario usuario = usuarioRepository.findById(dto.idUsuario());

        if (quarto == null || usuario == null) {
            throw new IllegalArgumentException("Quarto ou Usuário não encontrado.");
        }
        Reserva novaReserva = new Reserva(dto, quarto, usuario);
        repository.persist(novaReserva);
        return ReservaResponseDTO.valueOf(novaReserva);
    }

    @Override
    @Transactional
    public ReservaResponseDTO update(ReservaDTO dto, Long id) {
        Reserva reserva = repository.findById(id);
        Quarto quarto = quartoRepository.findById(dto.idQuarto());
        Usuario usuario = usuarioRepository.findById(dto.idUsuario());
        if (reserva == null || quarto == null || usuario == null) {
            throw new NotFoundException("Reserva, Quarto ou Usuário não encontrado");
        }

        reserva.atualizarComDTO(dto, quarto, usuario);
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
