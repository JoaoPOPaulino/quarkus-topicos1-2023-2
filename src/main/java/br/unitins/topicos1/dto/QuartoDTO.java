package br.unitins.topicos1.dto;

import java.util.Objects;

import br.unitins.topicos1.model.Quarto.TipoQuarto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class QuartoDTO {

    @Min(value = 1, message = "O número do quarto deve ser maior que 0")
    private final int numero;
    @NotBlank(message = "O tipo do quarto não pode estar em branco")
    private final TipoQuarto tipo;
    @Positive(message = "O preço do quarto deve ser um valor positivo")
    private final double preco;
    @NotNull(message = "O campo disponível não pode ser nulo")
    private final boolean disponivel;

    public QuartoDTO(int numero, TipoQuarto tipo, double preco, boolean disponivel) {
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    public int getNumero() {
        return numero;
    }

    public TipoQuarto getTipo() {
        return tipo;
    }

    public double getPreco() {
        return preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numero;
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + Double.hashCode(preco);
        result = prime * result + (disponivel ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        QuartoDTO other = (QuartoDTO) obj;
        return numero == other.numero &&
                Double.compare(other.preco, preco) == 0 &&
                Objects.equals(tipo, other.tipo) &&
                Objects.equals(disponivel, other.disponivel);
    }
}
