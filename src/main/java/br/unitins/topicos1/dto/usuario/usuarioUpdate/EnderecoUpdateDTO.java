package br.unitins.topicos1.dto.usuario.usuarioUpdate;

import br.unitins.topicos1.model.Endereco;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoUpdateDTO(
        @NotBlank(message = "O estado não pode ser nulo.") String estado,
        @NotBlank(message = "A cidade não pode ser nula.") String cidade,
        @NotBlank(message = "A quadra não pode ser nula.") String quadra,
        @NotBlank(message = "A rua não pode ser nula.") @Size(max = 100, message = "O nome da rua não pode ultrapassar 100 caracteres.") String rua,
        @Digits(integer = 6, fraction = 0, message = "O número deve ser numérico com até 6 dígitos.") Integer numero) {
    public static EnderecoUpdateDTO valueOf(Endereco endereco) {
        return new EnderecoUpdateDTO(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero());
    }

}
