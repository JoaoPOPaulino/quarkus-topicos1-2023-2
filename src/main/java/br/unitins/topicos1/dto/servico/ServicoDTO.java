package br.unitins.topicos1.dto.servico;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ServicoDTO(
                @NotNull(message = "O nome não pode ser nulo.") @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ ]+$", message = "O nome deve conter apenas letras e espaços.") String nome,

                @NotNull(message = "A descrição não pode ser nula") @Pattern(regexp = "^[A-Za-z0-9À-ÖØ-öø-ÿ ]+$", message = "A descrição deve conter apenas letras, números e espaços.") String descricao,

                @NotNull(message = "A hora inicial não pode ser nula.") LocalTime horaI,

                @NotNull(message = "A hora final não pode ser nula.") LocalTime horaF) {

}
