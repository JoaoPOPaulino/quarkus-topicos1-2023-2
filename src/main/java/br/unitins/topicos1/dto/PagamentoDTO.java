package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pagamento.TipoPagamento;

public class PagamentoDTO {

    private Long id;
    private TipoPagamento tipoPagamento; // você pode trocar para o tipo do Enum, se preferir
    private Long reservaId;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipo(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Long getReservaId() {
        return reservaId;
    }

    public void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }
}
