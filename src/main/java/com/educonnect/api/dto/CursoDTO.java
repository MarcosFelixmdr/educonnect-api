package com.educonnect.api.dto;

import com.educonnect.api.model.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;
    private String categoria;
    private Integer cargaHoraria;
    private Curso.NivelCurso nivel;

    @NotNull(message = "Educador é obrigatório")
    private Long educadorId;

    private String educadorNome;
    private Curso.StatusCurso status;
}
