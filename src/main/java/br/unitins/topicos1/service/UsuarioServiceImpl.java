package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.QuartoHotelDTO;
import br.unitins.topicos1.dto.QuartoHotelResponseDTO;
import br.unitins.topicos1.model.QuartoHotel;
import br.unitins.topicos1.repository.QuartoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    QuartoRepository repository;

    @Override
    @Transactional
    public QuartoHotelResponseDTO insert(QuartoHotelDTO dto) {
        QuartoHotel novoQuarto = new QuartoHotel();
        novoQuarto.setNumero(dto.getNumero());
        novoQuarto.setTipo(dto.getTipo());
        novoQuarto.setPreco(dto.getPreco());
        novoQuarto.setDisponivel(dto.getDisponivel());

        repository.persist(novoQuarto);

        return QuartoHotelResponseDTO.valueOf(novoQuarto);
    }

    @Override
    @Transactional
    public QuartoHotelResponseDTO update(QuartoHotelDTO dto, Long id) {
        QuartoHotel quartoHotel = repository.findById(id);

        if (quartoHotel != null) {
            quartoHotel.setNumero(dto.getNumero());
            quartoHotel.setTipo(dto.getTipo());
            quartoHotel.setPreco(dto.getPreco());
            quartoHotel.setDisponivel(dto.getDisponivel());
        } else
            throw new NotFoundException();

        return QuartoHotelResponseDTO.valueOf(quartoHotel);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public QuartoHotelResponseDTO findById(Long id) {
        return QuartoHotelResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<QuartoHotelResponseDTO> findByTipo(String tipo) {
        return repository.findByTipo(tipo).stream()
                .map(e -> QuartoHotelResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<QuartoHotelResponseDTO> findByAll() {
        return repository.listAll().stream()
                .map(e -> QuartoHotelResponseDTO.valueOf(e)).toList();
    }
}
