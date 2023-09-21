package br.unitins.topicos1.dto;

import java.util.Objects;

import br.unitins.topicos1.model.TipoQuarto;

public class QuartoDTO {
    private final int numero;
    private final TipoQuarto tipo;
    private final double preco;
    private final Boolean disponivel;

    public QuartoDTO(int numero, TipoQuarto tipo, double preco, Boolean disponivel) {
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

    public Boolean getDisponivel() {
        return disponivel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numero;
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
        result = prime * result + Double.hashCode(preco);
        result = prime * result + ((disponivel == null) ? 0 : disponivel.hashCode());
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
