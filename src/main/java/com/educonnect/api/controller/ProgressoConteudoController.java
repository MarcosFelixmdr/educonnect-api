package com.educonnect.api.controller;

import com.educonnect.api.service.ProgressoConteudoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progresso")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProgressoConteudoController {

    private final ProgressoConteudoService service;

    @GetMapping("/{aprendizId}")
    public ResponseEntity<List<Long>> listarConteudosConcluidos(@PathVariable Long aprendizId) {
        return ResponseEntity.ok(service.listarConteudosConcluidos(aprendizId));
    }

    @PostMapping
    public ResponseEntity<Void> alternarProgresso(@RequestBody ProgressoRequest request) {
        service.alternarProgresso(request.getAprendizId(), request.getConteudoId(), request.isConcluido());
        return ResponseEntity.ok().build();
    }

    @Data
    public static class ProgressoRequest {
        private Long aprendizId;
        private Long conteudoId;
        private boolean concluido;
    }
}
