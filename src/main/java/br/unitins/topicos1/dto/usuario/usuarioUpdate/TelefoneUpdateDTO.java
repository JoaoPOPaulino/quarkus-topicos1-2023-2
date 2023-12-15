package br.unitins.topicos1.dto.usuario.usuarioUpdate;

import br.unitins.topicos1.model.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TelefoneUpdateDTO(
        Long id,
        @NotBlank(message = "O código de área não pode ser nulo.") @Pattern(regexp = "^\\d{2}$", message = "O código de área deve conter 2 dígitos numéricos.") String codigoArea,

        @NotBlank(message = "O número não pode ser nulo.") @Pattern(regexp = "^\\d{4,5}-\\d{4}$", message = "Formato inválido para o número. Use o formato XXXX-XXXX ou XXXXX-XXXX.") String numero) {
    public static TelefoneUpdateDTO valueOf(Telefone telefone) {
        return new TelefoneUpdateDTO(
                telefone.getId(),
                telefone.getCodigoArea(),
                telefone.getNumero());
    }
}
