package br.com.cotiinformatica.api_agenda.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TarefaCategoriaDto {

    private String  categoria;
    private Long quantidade;

}
