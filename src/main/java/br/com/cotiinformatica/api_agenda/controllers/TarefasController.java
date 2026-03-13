package br.com.cotiinformatica.api_agenda.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefasController {

    @PostMapping
    public ResponseEntity<?> post() {
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
    }
}
