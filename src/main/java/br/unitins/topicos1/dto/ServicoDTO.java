package br.unitins.topicos1.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;


public record ServicoDTO(
    @NotNull(message = "O nome não pode ser nulo.")String nome,
    @NotNull(message = "A descrição não pode ser nula") String descricao,
    @NotNull(message = "A hora inicial não pode ser nula.") LocalDateTime horaI,
    @NotNull(message = "A hora final não pode ser nula.") LocalDateTime horaF

) {
    
}
