package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EnderecoDTO {
    @NotEmpty(message = "O estado não pode estar vazio")
    private String estado;

    @NotEmpty(message = "A cidade não pode estar vazia")
    private String cidade;

    @NotEmpty(message = "A quadra não pode estar vazia")
    private String quadra;

    @NotEmpty(message = "A rua não pode estar vazia")
    private String rua;

    @NotNull(message = "O número não pode estar vazio")
    private Integer numero;

    public EnderecoDTO() {

    }

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
        if (endereco == null) {
            return null;
        }

        return new EnderecoDTO(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero());
    }
}
