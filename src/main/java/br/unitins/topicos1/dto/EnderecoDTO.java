package br.unitins.topicos1.dto;

public record Endereco(
    String estado,
    String cidade,
    String quadra,
    String rua,
    String numero){
        public static EstadoDTO valueOf(Estado estado){
            return new EstadoDTO(
                estado.getEstado(),
                estado.getCidade(),
                estado.getQuadra(),
                estado.getRua(),
                estado.getNumer());
        }
}