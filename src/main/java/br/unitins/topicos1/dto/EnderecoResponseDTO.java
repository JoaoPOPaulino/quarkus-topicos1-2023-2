package br.unitins.topicos1.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class EnderecoResponseDTO {
    @NotBlank(message = "O estado não pode estar em branco")
    private String estado;

    @NotBlank(message = "A cidade não pode estar em branco")
    private String cidade;

    @NotBlank(message = "A quadra não pode estar em branco")
    private String quadra;

    @NotBlank(message = "A rua não pode estar em branco")
    private String rua;

    @NotNull(message = "O número não pode ser nulo")
    @PositiveOrZero(message = "O número deve ser um valor positivo ou zero")
    private Integer numero;

    public EnderecoResponseDTO(String estado, String cidade, String quadra, String rua, Integer numero) {
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

    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero());
    }

    public static List<EnderecoResponseDTO> mapToList(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
