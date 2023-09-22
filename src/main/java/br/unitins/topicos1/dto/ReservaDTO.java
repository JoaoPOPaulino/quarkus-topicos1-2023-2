package br.unitins.topicos1.dto;

import java.time.LocalDate;

public class ReservaDTO {

    private final Long id;
    private final Long usuarioId;
    private final Long quartoId;
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final Double precoTotal;

    public ReservaDTO(Long id, Long usuarioId, Long quartoId, LocalDate dataInicio, LocalDate dataFim,
            Double precoTotal) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.quartoId = quartoId;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.precoTotal = precoTotal;
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getQuartoId() {
        return quartoId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

}
