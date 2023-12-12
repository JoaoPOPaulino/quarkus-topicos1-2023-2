package br.unitins.topicos1.dto.Telefone;

import br.unitins.topicos1.model.Telefone;
import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO(
        @NotBlank(message = "O código de área não pode ser nulo.") String codigoArea,
        @NotBlank(message = "O numero não pode ser nulo.") String numero) {
    public static TelefoneDTO valueOf(Telefone telefone) {
        return new TelefoneDTO(
                telefone.getCodigoArea(),
                telefone.getNumero());
    }
}
