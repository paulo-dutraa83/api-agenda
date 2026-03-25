package br.com.cotiinformatica.api_agenda.dtos;

import br.com.cotiinformatica.api_agenda.enums.Prioridade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TarefaPrioridadeDto {

    private Prioridade prioridade;
    private Long quantidade;

}
