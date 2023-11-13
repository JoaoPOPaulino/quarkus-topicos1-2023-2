package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.Quarto;
import br.unitins.topicos1.model.Reserva;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.QuartoRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoServimeImpl implements PedidoService {

    @Inject
    QuartoRepository quartoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PedidoRepository repository;

    @Override
    @Transactional
    public PedidoResponseDTO insert(PedidoDTO dto, String login) {
        Pedido pedido = new Pedido();
        pedido.setDataHora(LocalDateTime.now());

        Double total = 0.0;

        for (ReservaDTO reservaDto : dto.reservas()) {
            total += (reservaDto.preco() + reservaDto.quantidade());
        }

        pedido.setTotalPedido(total);

        pedido.setReservas(new ArrayList<Reserva>());
        for (ReservaDTO reservaDto : dto.reservas()) {
            Reserva reserva = new Reserva();
            reserva.setDataFim(reservaDto.dataI());
            reserva.setDataFim(reservaDto.dateF());
            reserva.setPreco(reservaDto.preco());
            reserva.setQuantidade(reservaDto.quantidade());
            reserva.setPedido(pedido);
            Quarto quarto = quartoRepository.findById(reservaDto.idQuarto());
            reserva.setQuarto(quarto);

            quarto.setDisponivel(false);

            pedido.getReservas().add(reserva);
        }

        pedido.setUsuario(usuarioRepository.findByLogin(login));

        repository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO update(PedidoDTO dto, Long id) {
        Pedido pedido = repository.findById(id);

        if (pedido == null) {
            throw new NotFoundException("Pedido não encontrado");
        }

        Double total = 0.0;

        for (ReservaDTO reservaDto : dto.reservas()) {
            total += (reservaDto.preco() + reservaDto.quantidade());
        }

        pedido.setTotalPedido(total);

        List<Reserva> reservas = new ArrayList<>();
        for (ReservaDTO reservaDto : dto.reservas()) {
            Reserva reserva = new Reserva();
            reserva.setDataFim(reservaDto.dataI());
            reserva.setDataFim(reservaDto.dateF());
            reserva.setPreco(reservaDto.preco());
            reserva.setQuantidade(reservaDto.quantidade());
            reserva.setPedido(pedido);
            Quarto quarto = quartoRepository.findById(reservaDto.idQuarto());
            reserva.setQuarto(quarto);

            quarto.setDisponivel(false);

            reservas.add(reserva);
        }

        pedido.setReservas(reservas);

        repository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (!repository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findByAll() {
        return repository.listAll().stream().map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByAll(String login) {
        return repository.listAll().stream()
                .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

}
