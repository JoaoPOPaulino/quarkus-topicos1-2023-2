package br.unitins.topicos1.dto.usuario.usuarioUpdate;

import jakarta.validation.constraints.NotBlank;

public record NomeUpdateDTO(

                @NotBlank(message = "O campo nome não pode ser nulo.") String nome) {

}