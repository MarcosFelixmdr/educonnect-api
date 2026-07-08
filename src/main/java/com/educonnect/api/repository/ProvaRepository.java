package com.educonnect.api.repository;

import com.educonnect.api.model.Prova;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvaRepository extends JpaRepository<Prova, Long> {
    Optional<Prova> findByCursoId(Long cursoId);
    boolean existsByCursoId(Long cursoId);
}
