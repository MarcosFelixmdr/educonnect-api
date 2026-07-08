package com.educonnect.api.service;

import com.educonnect.api.model.Prova;
import com.educonnect.api.repository.ProvaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProvaService {

    private final ProvaRepository repository;

    public Optional<Prova> buscarPorCurso(Long cursoId) {
        return repository.findByCursoId(cursoId);
    }

    public Prova criar(Prova prova) {
        if (repository.existsByCursoId(prova.getCursoId())) {
            throw new IllegalStateException("Já existe uma prova para este curso.");
        }
        return repository.save(prova);
    }

    public Prova atualizar(Long id, Prova dados) {
        Prova prova = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada."));
        prova.setTitulo(dados.getTitulo());
        return repository.save(prova);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
