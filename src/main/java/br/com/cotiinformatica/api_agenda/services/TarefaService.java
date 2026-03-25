package br.com.cotiinformatica.api_agenda.services;

import br.com.cotiinformatica.api_agenda.dtos.*;
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

        //Capturar os dados e preencher a tarefa
        var tarefa = new Tarefa();

        tarefa.setNome(request.getNome());

        //Capturar e converter a data 'yyyy-MM-dd'
        var dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        tarefa.setData(LocalDate.parse(request.getData(), dataFormatter));

        //Capturar e converter a hora 'HH:mm'
        var horaFormatter = DateTimeFormatter.ofPattern("HH:mm");
        tarefa.setHora(LocalTime.parse(request.getHora(), horaFormatter));

        //Capturar o enum 'Prioridade'
        tarefa.setPrioridade(Prioridade.valueOf(request.getPrioridade()));

        //Verificar se a categoria não existe no banco de dados
        var categoria = categoriaRepository.findById(request.getCategoriaId());
        if (categoria.isEmpty()) {
            throw new IllegalArgumentException("A categoria informada não existe, verifique o ID.");
        }

        //Capturar a categoria, associando-a à tarefa
        tarefa.setCategoria(categoria.get());

        tarefa.setUsuarioId(UUID.randomUUID()); //provisório..

        //Salvar a tarefa no banco de dados
        tarefaRepository.save(tarefa);

        /// Retornar os dados
        return toResponse(tarefa);
    }

    public TarefaResponse atualizar(UUID id, TarefaRequest request) {

        //Buscando a tarefa no banco de dados através do ID
        var tarefaExistente = tarefaRepository.findById(id);
        if(tarefaExistente.isEmpty()) {
            throw new IllegalArgumentException("A tarefa não foi encontrada. Verifique o ID informado.");
        }

        //Gerar um objeto tarefa a partir da busca no banco de dados
        var tarefa = tarefaExistente.get();

        tarefa.setNome(request.getNome());

        //Capturar e converter a data 'yyyy-MM-dd'
        var dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        tarefa.setData(LocalDate.parse(request.getData(), dataFormatter));

        //Capturar e converter a hora 'HH:mm'
        var horaFormatter = DateTimeFormatter.ofPattern("HH:mm");
        tarefa.setHora(LocalTime.parse(request.getHora(), horaFormatter));

        //Capturar o enum 'Prioridade'
        tarefa.setPrioridade(Prioridade.valueOf(request.getPrioridade()));

        //Verificar se a categoria não existe no banco de dados
        var categoria = categoriaRepository.findById(request.getCategoriaId());
        if (categoria.isEmpty()) {
            throw new IllegalArgumentException("A categoria informada não existe, verifique o ID.");
        }

        //Capturar a categoria, associando-a à tarefa
        tarefa.setCategoria(categoria.get());

        tarefa.setUsuarioId(UUID.randomUUID()); //provisório..

        //Salvar a tarefa no banco de dados
        tarefaRepository.save(tarefa);

        /// Retornar os dados
        return toResponse(tarefa);
    }

    public TarefaResponse excluir(UUID id) {

        //Buscando a tarefa no banco de dados através do ID
        var tarefaExistente = tarefaRepository.findById(id);
        if(tarefaExistente.isEmpty()) {
            throw new IllegalArgumentException("A tarefa não foi encontrada. Verifique o ID informado.");
        }

        //Gerar um objeto tarefa a partir da busca no banco de dados
        var tarefa = tarefaExistente.get();

        //Excluir a tarefa no banco de dados
        tarefaRepository.delete(tarefa);

        /// Retornar os dados
        return toResponse(tarefa);
    }

    public List<TarefaResponse> consultarPorDatas(LocalDate dataMin, LocalDate dataMax) {

        //Consultar todas as tarefas do banco de dados
        var tarefas = tarefaRepository.findByDatas(dataMin, dataMax);

        //Gerar uma lista de TarefaResponse copiando os dados de cada tarefa
        var response = tarefas
                .stream()
                .map(this::toResponse)
                .toList();

        //Retornar a lista
        return response;
    }

    public TarefaResponse obterPorId(UUID id) {

        //Buscando a tarefa no banco de dados através do ID
        var tarefaExistente = tarefaRepository.findById(id);
        if(tarefaExistente.isEmpty()) {
            throw new IllegalArgumentException("A tarefa não foi encontrada. Verifique o ID informado.");
        }

        //Gerar um objeto tarefa a partir da busca no banco de dados
        var tarefa = tarefaExistente.get();

        /// Retornar os dados
        return toResponse(tarefa);
    }

    /*
        Metodo para retornar a consulta da quantidade de tarefas por categoria
     */
    public List<TarefaCategoriaDto> obterTarefasPorCategoria(LocalDate dataMin, LocalDate dataMax) {
        return tarefaRepository.groupByTarefaCategoria(dataMin, dataMax);
    }

    /*
        Metodo para retornar a consulta da quantidade de tarefas por prioridade
     */
    public List<TarefaPrioridadeDto> obterTarefasPorPrioridade(LocalDate dataMin, LocalDate dataMax) {
        return tarefaRepository.groupByTarefaPrioridade(dataMin, dataMax);
    }


    /*
        Método para copiar os dados da entidade para o responseDTO
     */
    private TarefaResponse toResponse(Tarefa tarefa) {
        //retornar os dados da tarefa
        var response = new TarefaResponse();

        response.setId(tarefa.getId());
        response.setNome(tarefa.getNome());
        response.setData(tarefa.getData().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        response.setHora(tarefa.getHora().format(DateTimeFormatter.ofPattern("HH:nn")));
        response.setPrioridade(tarefa.getPrioridade().toString());

        response.setCategoria(new CategoriaResponse());
        response.getCategoria().setId(tarefa.getCategoria().getId());
        response.getCategoria().setNome(tarefa.getCategoria().getNome());

        return response;
    }

}
