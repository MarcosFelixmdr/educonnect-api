package com.educonnect.api.repository;

import com.educonnect.api.model.ProgressoConteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressoConteudoRepository extends JpaRepository<ProgressoConteudo, Long> {
    List<ProgressoConteudo> findByAprendizId(Long aprendizId);
    Optional<ProgressoConteudo> findByAprendizIdAndConteudoId(Long aprendizId, Long conteudoId);
}
