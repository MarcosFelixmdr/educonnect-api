package com.educonnect.api.repository;

import com.educonnect.api.model.ResultadoProva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultadoProvaRepository extends JpaRepository<ResultadoProva, Long> {
    Optional<ResultadoProva> findByProvaIdAndAprendizId(Long provaId, Long aprendizId);
}
