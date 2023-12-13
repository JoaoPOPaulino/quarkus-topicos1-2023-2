package br.unitins.topicos1.dto.quarto;

import br.unitins.topicos1.dto.tipo.TipoQuartoDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record QuartoDTO(
                @NotNull(message = "O número do quarto não pode ser nulo.") @Positive(message = "O número do quarto deve ser um número positivo.") Integer numero,

                @NotNull(message = "O preço não pode ser nulo.") @Positive(message = "O preço deve ser um valor positivo.") Double preco,

                boolean disponivel,

                @NotNull(message = "O tipo do quarto não pode ser nulo.") TipoQuartoDTO tipoQuarto) {
}