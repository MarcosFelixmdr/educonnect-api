package com.educonnect.api.service;

import com.educonnect.api.model.ProgressoConteudo;
import com.educonnect.api.repository.ProgressoConteudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressoConteudoService {

    private final ProgressoConteudoRepository repository;

    public List<Long> listarConteudosConcluidos(Long aprendizId) {
        return repository.findByAprendizId(aprendizId).stream()
                .filter(ProgressoConteudo::isConcluido)
                .map(ProgressoConteudo::getConteudoId)
                .collect(Collectors.toList());
    }

    public void alternarProgresso(Long aprendizId, Long conteudoId, boolean concluido) {
        Optional<ProgressoConteudo> existing = repository.findByAprendizIdAndConteudoId(aprendizId, conteudoId);
        if (existing.isPresent()) {
            ProgressoConteudo progresso = existing.get();
            progresso.setConcluido(concluido);
            repository.save(progresso);
        } else {
            ProgressoConteudo progresso = ProgressoConteudo.builder()
                    .aprendizId(aprendizId)
                    .conteudoId(conteudoId)
                    .concluido(concluido)
                    .build();
            repository.save(progresso);
        }
    }
}
