package com.educonnect.api.repository;

import com.educonnect.api.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    List<Matricula> findByAprendizId(Long aprendizId);
    List<Matricula> findByCursoId(Long cursoId);
    List<Matricula> findByStatus(Matricula.StatusMatricula status);
}
