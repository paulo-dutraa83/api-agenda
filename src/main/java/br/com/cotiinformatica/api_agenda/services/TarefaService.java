package br.com.cotiinformatica.api_agenda.services;

import br.com.cotiinformatica.api_agenda.dtos.CategoriaResponse;
import br.com.cotiinformatica.api_agenda.dtos.TarefaRequest;
import br.com.cotiinformatica.api_agenda.dtos.TarefaResponse;
import br.com.cotiinformatica.api_agenda.entities.Categoria;
import br.com.cotiinformatica.api_agenda.entities.Tarefa;
import br.com.cotiinformatica.api_agenda.enums.Prioridade;
import br.com.cotiinformatica.api_agenda.repositories.CategoriaRepository;
import br.com.cotiinformatica.api_agenda.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public TarefaResponse cadastrar(TarefaRequest request) {

        //Capturando os dados e preenchendo as tarefas
        var tarefa = new Tarefa();

        tarefa.setNome(request.getNome());

        //Capturando e convertendo a data 'yyyyy-MM-dd'
        var dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        tarefa.setData(LocalDate.parse(request.getData(), dataFormatter));

        //Capturando e convertendo a hora 'HH:mm'
        var horaFormatter = DateTimeFormatter.ofPattern("HH:mm");
        tarefa.setHora(LocalTime.parse(request.getHora(), horaFormatter));

        //Capturando o enum 'Prioridade'
        tarefa.setPrioridade(Prioridade.valueOf(request.getPrioridade()));

        //Verificando se a categoria não existe no banco de dados
        var categoria = categoriaRepository.findById(request.getCategoriaId());
        if (categoria.isEmpty()) {
            throw  new IllegalArgumentException("A categoria informada não existe, verifique o ID");
        }

        //Capturando a categoria, associando-a à tarefa
        tarefa.setCategoria(categoria.get());

        tarefa.setUsuarioId(UUID.randomUUID());

        //Salvando no banco de dados
        tarefaRepository.save(tarefa);

        //Retornando os dados
        return toResponse(tarefa);
    }

    //Método para consultar as tarefas
    public List<TarefaResponse> consultar() {

        //Consultar todas as categorias do banco de dados
        var tarefas = tarefaRepository.findAll();

        //Gerar uma lista de CategoriaResponse copiando os dados de cada categoria
        var response = tarefas
                .stream()
                .map(this::toResponse)
                .toList();

        //Retornar a lista
        return response;
    }

    /*
        Método privado da classe para copiar as informações
        da entidade para o DTO
     */
    private TarefaResponse toResponse(Tarefa tarefa) {
        //Retornando os dados da tarefa
        var response = new TarefaResponse();

        response.setId(tarefa.getId());
        response.setNome(tarefa.getNome());
        response.setData(tarefa.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        response.setHora(tarefa.getHora().format(DateTimeFormatter.ofPattern("HH:mm")));
        response.setPrioridade(tarefa.getPrioridade().toString());

        response.setCategoria(new CategoriaResponse());
        response.getCategoria().setId(tarefa.getCategoria().getId());
        response.getCategoria().setNome(tarefa.getCategoria().getNome());

        return response;
    }
}
