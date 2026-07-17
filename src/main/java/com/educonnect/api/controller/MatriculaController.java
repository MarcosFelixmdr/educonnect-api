package com.educonnect.api.controller;

import com.educonnect.api.dto.MatriculaDTO;
import com.educonnect.api.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> listarTodos() {
        return ResponseEntity.ok(matriculaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(matriculaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> criar(@Valid @RequestBody MatriculaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDTO> atualizar(@PathVariable Long id, @RequestBody MatriculaDTO dto) {
        return ResponseEntity.ok(matriculaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        matriculaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/validar/{hash}")
    public ResponseEntity<MatriculaDTO> buscarPorCertificadoHash(@PathVariable String hash) {
        return ResponseEntity.ok(matriculaService.buscarPorCertificadoHash(hash));
    }
}

