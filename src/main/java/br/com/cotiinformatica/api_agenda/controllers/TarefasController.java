package br.com.cotiinformatica.api_agenda.controllers;

import br.com.cotiinformatica.api_agenda.dtos.TarefaRequest;
import br.com.cotiinformatica.api_agenda.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefasController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<?> post(@Valid @RequestBody TarefaRequest request) {

        var response = tarefaService.cadastrar(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable UUID id, @RequestBody TarefaRequest request) {

        var response = tarefaService.atualizar(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {

        var response = tarefaService.excluir(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{dataMin}/{dataMax}")
    public ResponseEntity<?> get(@PathVariable LocalDate dataMin, @PathVariable LocalDate dataMax) {
        var response = tarefaService.consultarPorDatas(dataMin, dataMax);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        var response = tarefaService.obterPorId(id);
        return ResponseEntity.ok().body(response);
    }
}
