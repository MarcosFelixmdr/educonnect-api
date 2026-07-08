package com.educonnect.api.controller;

import com.educonnect.api.model.Prova;
import com.educonnect.api.service.ProvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/provas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProvaController {

    private final ProvaService service;

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<Prova> buscarPorCurso(@PathVariable Long cursoId) {
        Optional<Prova> prova = service.buscarPorCurso(cursoId);
        return prova.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Prova> criar(@RequestBody Prova prova) {
        return ResponseEntity.ok(service.criar(prova));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prova> atualizar(@PathVariable Long id, @RequestBody Prova prova) {
        return ResponseEntity.ok(service.atualizar(id, prova));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
