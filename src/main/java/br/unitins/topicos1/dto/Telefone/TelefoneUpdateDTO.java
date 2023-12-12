package br.unitins.topicos1.dto.Telefone;

import br.unitins.topicos1.model.Telefone;
import jakarta.validation.constraints.NotBlank;

public record TelefoneUpdateDTO(
        Long id,
        @NotBlank(message = "O código de área não pode ser nulo.") String codigoArea,
        @NotBlank(message = "O numero não pode ser nulo.") String numero) {
    public static TelefoneUpdateDTO valueOf(Telefone telefone) {
        return new TelefoneUpdateDTO(
                telefone.getId(),
                telefone.getCodigoArea(),
                telefone.getNumero());
    }
}
