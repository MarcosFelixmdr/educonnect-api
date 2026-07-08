package com.educonnect.api.service;

import com.educonnect.api.model.Questao;
import com.educonnect.api.repository.QuestaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private static final int MAX_QUESTOES = 10;

    private final QuestaoRepository repository;

    public List<Questao> listarPorProva(Long provaId) {
        return repository.findByProvaIdOrderByOrdem(provaId);
    }

    public Questao criar(Questao questao) {
        long count = repository.countByProvaId(questao.getProvaId());
        if (count >= MAX_QUESTOES) {
            throw new IllegalStateException("A prova já possui o máximo de " + MAX_QUESTOES + " questões.");
        }
        questao.setOrdem((int) count + 1);
        return repository.save(questao);
    }

    public Questao atualizar(Long id, Questao dados) {
        Questao questao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questão não encontrada."));
        questao.setEnunciado(dados.getEnunciado());
        questao.setOpcaoA(dados.getOpcaoA());
        questao.setOpcaoB(dados.getOpcaoB());
        questao.setOpcaoC(dados.getOpcaoC());
        questao.setOpcaoD(dados.getOpcaoD());
        questao.setRespostaCorreta(dados.getRespostaCorreta());
        return repository.save(questao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
