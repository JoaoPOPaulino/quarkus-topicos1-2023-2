package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TelefoneDTO(
        @NotBlank(message = "O código de área não pode estar em branco") @Pattern(regexp = "\\d{2}", message = "O código de área deve conter exatamente 2 dígitos") String codigoArea,

        @NotBlank(message = "O número de telefone não pode estar em branco") @Pattern(regexp = "\\d{8,12}", message = "O número de telefone deve conter entre 8 e 12 dígitos") String numero) {
    public static TelefoneDTO valueOf(Telefone telefone) {
        return new TelefoneDTO(
                telefone.getCodigoArea(),
                telefone.getNumero());
    }
}