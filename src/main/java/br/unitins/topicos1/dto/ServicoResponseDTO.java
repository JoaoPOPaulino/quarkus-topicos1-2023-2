package br.unitins.topicos1.dto;

import java.time.LocalDate;

import br.unitins.topicos1.model.Servico;

public record ServicoResponseDTO(
    Long id,
    String nome,
    String descricao,
    LocalDate horaInicio,
    LocalDate horaFim
) { 
    private static ServicoResponseDTO valueOf(Servico servico){
        return new ServicoResponseDTO(
                    servico.getId(),
                    servico.getNome(),
                    servico.getDescricao(),
                    servico.getHoraInicio(),
                    servico.getHoraFim());
    }
}
