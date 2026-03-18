package br.com.cotiinformatica.api_agenda.controllers;

import br.com.cotiinformatica.api_agenda.dtos.TarefaRequest;
import br.com.cotiinformatica.api_agenda.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<?> put() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> get() {
        var response = tarefaService.consultar();
        return ResponseEntity.ok().body(response);
    }
}
