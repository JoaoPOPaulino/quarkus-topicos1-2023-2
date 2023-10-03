package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento.TipoPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record PagamentoDTO(
        @NotBlank(message = "O tipo de pagamento não pode estar em branco") @TipoPagamentoValido(allowedTypes = {
                "Cartão de Crédito", "Cartão de Débito", "Boleto", "Pix" }) TipoPagamento tipoPagamento,

        @NotEmpty(message = "O ID não pode estar vazio") Long reservaId

    ){

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public Long getReservaId() {
        return reservaId;
    }

}
