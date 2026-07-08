package com.educonnect.api.controller;

import com.educonnect.api.model.Avaliacao;
import com.educonnect.api.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoService service;

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Avaliacao>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(service.listarPorCurso(cursoId));
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criar(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(service.criar(avaliacao));
    }
}
