package br.com.cotiinformatica.api_agenda.controllers;

import br.com.cotiinformatica.api_agenda.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("tarefas-categoria/{dataMin}/{dataMax}")
    public ResponseEntity<?> getTarefasCategoria(@PathVariable LocalDate dataMin, @PathVariable LocalDate dataMax) {
        var response = tarefaService.obterTarefasPorCategoria(dataMin, dataMax);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("tarefas-prioridade/{dataMin}/{dataMax}")
    public ResponseEntity<?> getTarefasPrioridade(@PathVariable LocalDate dataMin, @PathVariable LocalDate dataMax) {
        var response = tarefaService.obterTarefasPorCategoria(dataMin, dataMax);
        return ResponseEntity.status(200).body(response);
    }

}
