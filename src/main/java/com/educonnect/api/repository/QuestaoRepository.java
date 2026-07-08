package com.educonnect.api.repository;

import com.educonnect.api.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    List<Questao> findByProvaIdOrderByOrdem(Long provaId);
    long countByProvaId(Long provaId);
    void deleteByProvaId(Long provaId);
}
