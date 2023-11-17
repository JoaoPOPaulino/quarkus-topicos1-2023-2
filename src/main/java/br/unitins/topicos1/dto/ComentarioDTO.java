package br.unitins.topicos1.dto;

public record ComentarioDTO() {
    @NotNull(message = "O nome não pode ser nulo.")String nome,
    @NotNull(message = "A descrição não pode ser nula") String descricao,

}
