package br.unitins.topicos1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.oracle.svm.core.annotate.Inject;

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
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServimeImpl implements PedidoService {

    @Inject
    QuartoRepository quartoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @jakarta.inject.Inject
    PedidoRepository pedidoRepository;

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

        pedidoRepository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findByAll() {
        return pedidoRepository.listAll().stream().map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByAll(String login) {
        return pedidoRepository.listAll().stream()
                .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

}
