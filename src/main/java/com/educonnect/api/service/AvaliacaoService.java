package com.educonnect.api.service;

import com.educonnect.api.model.Avaliacao;
import com.educonnect.api.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository repository;

    public List<Avaliacao> listarPorCurso(Long cursoId) {
        return repository.findByCursoIdOrderByCreatedAtDesc(cursoId);
    }

    public Avaliacao criar(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }
}
