package br.unitins.topicos1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ReservaDTO {

    @NotNull(message = "O ID do usuário não pode ser nulo")
    @Positive(message = "O ID do usuário deve ser um valor positivo")
    private Long usuarioId;

    @NotNull(message = "O ID do quarto não pode ser nulo")
    @Positive(message = "O ID do quarto deve ser um valor positivo")
    private Long quartoId;

    @NotNull(message = "O ID do pagamento não pode ser nulo")
    @Positive(message = "O ID do pagamento deve ser um valor positivo")
    private Long pagamentoId;

    @NotNull(message = "A data de início não pode ser nula")
    @FutureOrPresent(message = "A data de início deve ser futura ou a data atual")
    private LocalDate dataInicio;

    @NotNull(message = "A data de início não pode ser nula")
    @FutureOrPresent(message = "A data de início deve ser futura ou a data atual")
    private LocalDate dataFim;

    public ReservaDTO(Long idUsuario, String string, String string2) {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getQuartoId() {
        return quartoId;
    }

    public Long getPagamentoId() {
        return pagamentoId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

}
