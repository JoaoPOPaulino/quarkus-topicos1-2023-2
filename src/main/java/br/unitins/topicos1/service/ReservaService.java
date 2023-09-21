package br.unitins.topicos1.service;

import java.util.List;
import br.unitins.topicos1.dto.ReservaDTO;
import br.unitins.topicos1.dto.ReservaResponseDTO;

public interface ReservaService {

    public ReservaResponseDTO insert(ReservaDTO dto);

    public ReservaResponseDTO update(ReservaDTO dto, Long id);

    public void delete(Long id);

    public ReservaResponseDTO findById(Long id);

    public List<ReservaResponseDTO> findByAll();

}
