package com.educonnect.api.repository;

import com.educonnect.api.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByCursoIdOrderByCreatedAtDesc(Long cursoId);
}
