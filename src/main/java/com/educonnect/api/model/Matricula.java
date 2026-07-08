package com.educonnect.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "matriculas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Aprendiz é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Usuario aprendiz;

    @NotNull(message = "Curso é obrigatório")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(name = "data_inscricao")
    @Builder.Default
    private LocalDate dataInscricao = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private StatusMatricula status = StatusMatricula.PENDENTE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public enum StatusMatricula {
        PENDENTE, ATIVA, CONCLUIDA, CANCELADA
    }
}
