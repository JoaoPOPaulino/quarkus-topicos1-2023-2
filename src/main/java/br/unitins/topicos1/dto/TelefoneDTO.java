package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class TelefoneDTO {
    @NotBlank(message = "O código de área não pode estar em branco")
    @Pattern(regexp = "\\d{2}", message = "O código de área deve conter exatamente 2 dígitos")
    private String codigoArea;

    @NotBlank(message = "O número de telefone não pode estar em branco")
    @Pattern(regexp = "\\d{8,9}", message = "O número de telefone deve conter entre 8 e 9 dígitos")
    private String numero;

    public TelefoneDTO(String codigoArea, String numero) {
        this.codigoArea = codigoArea;
        this.numero = numero;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public String getNumero() {
        return numero;
    }

    public static TelefoneDTO valueOf(Telefone telefone) {
        return new TelefoneDTO(telefone.getCodigoArea(), telefone.getNumero());
    }

}
