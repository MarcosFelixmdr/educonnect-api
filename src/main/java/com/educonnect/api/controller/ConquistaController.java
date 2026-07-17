package com.educonnect.api.controller;

import com.educonnect.api.model.Conquista;
import com.educonnect.api.service.ConquistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conquistas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ConquistaController {

    private final ConquistaService service;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Conquista>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }
}
