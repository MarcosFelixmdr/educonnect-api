package com.educonnect.api.repository;

import com.educonnect.api.model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConquistaRepository extends JpaRepository<Conquista, Long> {
    List<Conquista> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndTipo(Long usuarioId, String tipo);
}
