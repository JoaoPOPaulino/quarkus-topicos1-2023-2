package br.unitins.topicos1.dto;

import java.util.Objects;

public class QuartoHotelDTO {
    private final int numero;
    private final String tipo;
    private final double preco;
    private final Boolean disponivel;

    public QuartoHotelDTO(int numero, String tipo, double preco, Boolean disponivel) {
        this.numero = numero;
        this.tipo = tipo;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
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
        QuartoHotelDTO other = (QuartoHotelDTO) obj;
        return numero == other.numero &&
                Double.compare(other.preco, preco) == 0 &&
                Objects.equals(tipo, other.tipo) &&
                Objects.equals(disponivel, other.disponivel);
    }
}
