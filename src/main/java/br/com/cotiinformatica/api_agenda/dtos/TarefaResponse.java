package br.com.cotiinformatica.api_agenda.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TarefaResponse {

    private UUID id;
    private String nome;
    private String data;
    private String hora;
    private String prioridade;
    private CategoriaResponse categoria;

}
