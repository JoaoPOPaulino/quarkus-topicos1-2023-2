package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public class EnderecoDTO {
    @NotBlank(message = "O estado não pode estar vazio.")
    private String estado;

    @NotBlank(message = "A cidade não pode estar vazia.")
    private String cidade;

    @NotBlank(message = "A quadra não pode estar vazia.")
    private String quadra;

    @NotBlank(message = "A rua não pode estar vazia.")
    private String rua;

    @NotBlank(message = "O número não pode estar vazio.")
    private Integer numero;

    public EnderecoDTO(String estado, String cidade, String quadra, String rua, Integer numero) {
        this.estado = estado;
        this.cidade = cidade;
        this.quadra = quadra;
        this.rua = rua;
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getQuadra() {
        return quadra;
    }

    public String getRua() {
        return rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public static EnderecoDTO valueOf(Endereco endereco) {

        return new EnderecoDTO(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero());
    }
}
