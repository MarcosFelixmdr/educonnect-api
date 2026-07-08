package com.educonnect.api.controller;

import com.educonnect.api.model.Questao;
import com.educonnect.api.service.QuestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestaoController {

    private final QuestaoService service;

    @GetMapping("/prova/{provaId}")
    public ResponseEntity<List<Questao>> listarPorProva(@PathVariable Long provaId) {
        return ResponseEntity.ok(service.listarPorProva(provaId));
    }

    @PostMapping
    public ResponseEntity<Questao> criar(@RequestBody Questao questao) {
        return ResponseEntity.ok(service.criar(questao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Questao> atualizar(@PathVariable Long id, @RequestBody Questao questao) {
        return ResponseEntity.ok(service.atualizar(id, questao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
