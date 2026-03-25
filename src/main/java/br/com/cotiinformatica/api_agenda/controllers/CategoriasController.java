package br.com.cotiinformatica.api_agenda.controllers;

import br.com.cotiinformatica.api_agenda.dtos.CategoriaRequest;
import br.com.cotiinformatica.api_agenda.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid CategoriaRequest request) {
        return ResponseEntity.ok().body(categoriaService.cadastrar(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> put(@PathVariable UUID id, @RequestBody @Valid CategoriaRequest request) {
        return ResponseEntity.ok().body(categoriaService.atualizar(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return ResponseEntity.ok().body(categoriaService.excluir(id));
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(categoriaService.consultar());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(categoriaService.obterPorId(id));
    }
}
