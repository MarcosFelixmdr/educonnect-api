package com.educonnect.api.dto;

import com.educonnect.api.model.Matricula;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDTO {

    private Long id;

    @NotNull(message = "Aprendiz é obrigatório")
    private Long aprendizId;

    private String aprendizNome;

    @NotNull(message = "Curso é obrigatório")
    private Long cursoId;

    private String cursoTitulo;
    private LocalDate dataInscricao;
    private Matricula.StatusMatricula status;
    private LocalDateTime createdAt;
}
