package br.unitins.topicos1.dto.tipo;

import br.unitins.topicos1.model.TipoPagamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TipoPagamentoDTO(
        @Min(value = 1, message = "O ID do tipo de pagamento deve ser positivo.") Integer id,

        @NotBlank(message = "A descrição do tipo de pagamento não pode ser vazia.") @Pattern(regexp = "^[A-Za-z ]+$", message = "A descrição deve conter apenas letras e espaços.") String label) {

    public static TipoPagamentoDTO valueOf(TipoPagamento tipoPagamento) {
        return new TipoPagamentoDTO(
                tipoPagamento.getId(),
                tipoPagamento.getLabel());
    }
}