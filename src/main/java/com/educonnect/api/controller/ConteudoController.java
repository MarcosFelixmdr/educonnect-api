package com.educonnect.api.controller;

import com.educonnect.api.dto.ConteudoDTO;
import com.educonnect.api.service.ConteudoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conteudos")
@RequiredArgsConstructor
public class ConteudoController {

    private final ConteudoService conteudoService;

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<ConteudoDTO>> listarPorCurso(@PathVariable Long cursoId) {
        return ResponseEntity.ok(conteudoService.listarPorCurso(cursoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(conteudoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConteudoDTO> criar(@Valid @RequestBody ConteudoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conteudoService.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConteudoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ConteudoDTO dto) {
        return ResponseEntity.ok(conteudoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        conteudoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
