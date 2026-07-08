package com.educonnect.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "progresso_conteudo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"aprendiz_id", "conteudo_id"})
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgressoConteudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "aprendiz_id", nullable = false)
    private Long aprendizId;

    @Column(name = "conteudo_id", nullable = false)
    private Long conteudoId;

    @Column(nullable = false)
    private boolean concluido;
}
