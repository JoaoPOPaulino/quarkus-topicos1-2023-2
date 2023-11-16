package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;
import br.unitins.topicos1.model.Pagamento;

public interface ReservaService {

    ReservaResponseDTO insert(ReservaDTO dto);

    ReservaResponseDTO update(ReservaDTO dto, Long id);

    void delete(Long id);

    ReservaResponseDTO findById(Long id);

    List<ReservaResponseDTO> findByAll();

    void atualizarReservaComPagamento(Long id, Pagamento pagamento);

}
