package br.com.cotiinformatica.api_agenda.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TarefaRequest {

    @Size(min = 6, max = 150, message = "Informe no mínimo 6 e no máximo 150 caracteres.")
    @NotEmpty(message = "Por favor, informe o nome da tarefa.")
    private String nome;

    @NotEmpty(message = "Por favor, informe a data da tarefa.")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "A data deve estar no formato yyyy-MM-dd."
    )
    private String data;

    @NotEmpty(message = "Por favor, informe a hora da tarefa.")
    @Pattern(
            regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$",
            message = "A hora deve estar no formato HH:mm (00:00 até 23:59)."
    )
    private String hora;

    @NotEmpty(message = "Por favor, informe a prioridade da tarefa.")
    @Pattern(
            regexp = "^(ALTA|MEDIA|BAIXA)$",
            message = "A prioridade deve ser ALTA, MEDIA ou BAIXA."
    )
    private String prioridade;

    @NotNull(message = "Por favor, informe a categoria da tarefa.")
    private UUID categoriaId;
}