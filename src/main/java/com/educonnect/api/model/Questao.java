package com.educonnect.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questoes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "prova_id", nullable = false)
    private Long provaId;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @NotBlank
    @Column(name = "opcao_a", nullable = false)
    private String opcaoA;

    @NotBlank
    @Column(name = "opcao_b", nullable = false)
    private String opcaoB;

    @NotBlank
    @Column(name = "opcao_c", nullable = false)
    private String opcaoC;

    @NotBlank
    @Column(name = "opcao_d", nullable = false)
    private String opcaoD;

    /**
     * Resposta correta: "A", "B", "C" ou "D"
     */
    @NotBlank
    @Column(name = "resposta_correta", nullable = false, length = 1)
    private String respostaCorreta;

    @Column(nullable = false)
    private Integer ordem;
}
