package com.educonnect.api.service;

import com.educonnect.api.dto.SubmissaoDTO;
import com.educonnect.api.model.Questao;
import com.educonnect.api.model.ResultadoProva;
import com.educonnect.api.repository.QuestaoRepository;
import com.educonnect.api.repository.ResultadoProvaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultadoProvaService {

    private static final int ACERTOS_MINIMOS = 6;

    private final ResultadoProvaRepository repository;
    private final QuestaoRepository questaoRepository;

    public Optional<ResultadoProva> buscarResultado(Long provaId, Long aprendizId) {
        return repository.findByProvaIdAndAprendizId(provaId, aprendizId);
    }

    public ResultadoProva submeter(SubmissaoDTO dto) {
        List<Questao> questoes = questaoRepository.findByProvaIdOrderByOrdem(dto.getProvaId());
        Map<Long, String> respostas = dto.getRespostas();

        int acertos = 0;
        for (Questao q : questoes) {
            String respostaAluno = respostas.get(q.getId());
            if (respostaAluno != null && respostaAluno.equalsIgnoreCase(q.getRespostaCorreta())) {
                acertos++;
            }
        }

        boolean aprovado = acertos >= ACERTOS_MINIMOS;

        
        repository.findByProvaIdAndAprendizId(dto.getProvaId(), dto.getAprendizId())
                .ifPresent(r -> repository.deleteById(r.getId()));

        ResultadoProva resultado = ResultadoProva.builder()
                .provaId(dto.getProvaId())
                .aprendizId(dto.getAprendizId())
                .acertos(acertos)
                .aprovado(aprovado)
                .build();

        return repository.save(resultado);
    }
}
