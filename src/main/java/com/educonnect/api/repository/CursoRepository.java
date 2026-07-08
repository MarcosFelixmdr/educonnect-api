package com.educonnect.api.repository;

import com.educonnect.api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByStatus(Curso.StatusCurso status);
    List<Curso> findByEducadorId(Long educadorId);
}
