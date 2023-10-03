package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Telefone;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TelefoneResponseDTO(
        Long id,
        @NotBlank(message = "O código de área não pode estar em branco") @Pattern(regexp = "\\d{2}", message = "O código de área deve conter exatamente 2 dígitos") String codigoArea,
        @NotBlank(message = "O número de telefone não pode estar em branco") @Pattern(regexp = "\\d{8,12}", message = "O número de telefone deve conter entre 8 e 12 dígitos") String numero) {

    public static TelefoneResponseDTO valueOf(@NotNull Telefone telefone) {
        return new TelefoneResponseDTO(
                telefone.getId(),
                telefone.getCodigoArea(),
                telefone.getNumero());
    }

    public static List<TelefoneResponseDTO> mapToList(List<Telefone> telefones) {
        return telefones.stream()
                .map(TelefoneResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
