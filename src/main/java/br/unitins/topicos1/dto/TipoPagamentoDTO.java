package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.TipoPagamento;

public record TipoPagamentoDTO(
        Integer id,
        String label) {

    public static TipoPagamentoDTO valueOf(TipoPagamento tipoPagamento) {
        return new TipoPagamentoDTO(
                tipoPagamento.getId(),
                tipoPagamento.getLabel());
    }
}
