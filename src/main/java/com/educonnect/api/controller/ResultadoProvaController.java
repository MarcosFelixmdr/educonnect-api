package com.educonnect.api.controller;

import com.educonnect.api.dto.SubmissaoDTO;
import com.educonnect.api.model.ResultadoProva;
import com.educonnect.api.service.ResultadoProvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resultados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResultadoProvaController {

    private final ResultadoProvaService service;

    @GetMapping("/prova/{provaId}/aprendiz/{aprendizId}")
    public ResponseEntity<ResultadoProva> buscarResultado(
            @PathVariable Long provaId,
            @PathVariable Long aprendizId) {
        return service.buscarResultado(provaId, aprendizId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/submeter")
    public ResponseEntity<ResultadoProva> submeter(@RequestBody SubmissaoDTO dto) {
        return ResponseEntity.ok(service.submeter(dto));
    }
}
