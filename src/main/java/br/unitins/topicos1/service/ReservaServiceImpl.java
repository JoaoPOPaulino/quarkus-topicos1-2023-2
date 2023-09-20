package br.unitins.topicos1.service;

import java.time.temporal.ChronoUnit;
import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.model.QuartoHotel;
import br.unitins.topicos1.repository.ReservaRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import br.unitins.topicos1.repository.QuartoRepository;
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

    @Override
    @Transactional
    public ReservaResponseDTO insert(ReservaDTO dto) {
        Reserva novaReserva = new Reserva();
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId());
        QuartoHotel quarto = quartoRepository.findById(dto.getQuartoId());
        
        if (usuario == null || quarto == null)
            throw new NotFoundException("Usuário ou Quarto não encontrado.");
        
        novaReserva.setUsuario(usuario);
        novaReserva.setQuarto(quarto);
        novaReserva.setDataInicio(dto.getDataInicio());
        novaReserva.setDataFim(dto.getDataFim());
        
        long numeroDias = ChronoUnit.DAYS.between(dto.getDataInicio(), dto.getDataFim());
        double precoTotal = numeroDias * quarto.getPreco();
        novaReserva.setPrecoTotal(precoTotal);

        repository.persist(novaReserva);

        return ReservaResponseDTO.valueOf(novaReserva);
    }

    @Override
    @Transactional
    public ReservaResponseDTO update(ReservaDTO dto, Long id) {
        Reserva reservaExistente = repository.findById(id);
        if (reservaExistente == null)
            throw new NotFoundException("Reserva não encontrada.");

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId());
        QuartoHotel quarto = quartoRepository.findById(dto.getQuartoId());

        if (usuario == null || quarto == null)
            throw new NotFoundException("Usuário ou Quarto não encontrado.");

        reservaExistente.setUsuario(usuario);
        reservaExistente.setQuarto(quarto);
        reservaExistente.setDataInicio(dto.getDataInicio());
        reservaExistente.setDataFim(dto.getDataFim());

        long numeroDias = ChronoUnit.DAYS.between(dto.getDataInicio(), dto.getDataFim());
        double precoTotal = numeroDias * quarto.getPreco();
        reservaExistente.setPrecoTotal(precoTotal);

        return ReservaResponseDTO.valueOf(reservaExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException("Reserva não encontrada.");
    }

    @Override
    public ReservaResponseDTO findById(Long id) {
        Reserva reserva = repository.findById(id);
        if (reserva == null)
            throw new NotFoundException("Reserva não encontrada.");
        return ReservaResponseDTO.valueOf(reserva);
    }

    @Override
    public List<ReservaResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(ReservaResponseDTO::valueOf).collect(Collectors.toList());
    }

}
