package br.unitins.topicos1.dto;

import java.time.LocalDate;

public class ReservaDTO {

    private final Long usuarioId;
    private final Long quartoId;
    private final LocalDate dataInicio;
    private final LocalDate dataFinal;

    public ReservaDTO(Long usuarioId, Long quartoId, LocalDate dataInicio, LocalDate dataFinal) {
        this.usuarioId = usuarioId;
        this.quartoId = quartoId;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getQuartoId() {
        return quartoId;
    }

}
