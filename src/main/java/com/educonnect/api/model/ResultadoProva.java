package com.educonnect.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "resultados_prova",
    uniqueConstraints = @UniqueConstraint(columnNames = {"prova_id", "aprendiz_id"})
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoProva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "prova_id", nullable = false)
    private Long provaId;

    @NotNull
    @Column(name = "aprendiz_id", nullable = false)
    private Long aprendizId;

    
    @Column(nullable = false)
    private Integer acertos;

    
    @Column(nullable = false)
    private Boolean aprovado;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
