package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.model.Pedido;
import jakarta.validation.Valid;

public interface PedidoService {

    PedidoResponseDTO insert(PedidoDTO dto, String login);

    PedidoResponseDTO update(PedidoDTO dto, Long id);

    void delete(Long id);

    PedidoResponseDTO findById(Long id);

    List<PedidoResponseDTO> findByAll();

    List<PedidoResponseDTO> findByAll(String login);

}
