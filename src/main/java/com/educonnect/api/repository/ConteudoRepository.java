package com.educonnect.api.repository;

import com.educonnect.api.model.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {
    List<Conteudo> findByCursoIdOrderByOrdemAsc(Long cursoId);
}
