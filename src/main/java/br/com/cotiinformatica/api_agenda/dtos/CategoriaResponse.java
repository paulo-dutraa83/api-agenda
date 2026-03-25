package br.com.cotiinformatica.api_agenda.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CategoriaResponse {

    private UUID id;

    private String nome;
}
