package br.com.cotiinformatica.api_agenda.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoriaRequest {

    @Size(min = 6, message = "Por favor, informe no mínimo 6 caracteres.")
    @NotEmpty(message = "Por favor, informe o nome da categoria.")
    private String nome;


}
