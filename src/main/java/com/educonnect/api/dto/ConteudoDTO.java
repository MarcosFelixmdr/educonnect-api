package com.educonnect.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoDTO {

    private Long id;

    @NotBlank(message = "Título do conteúdo é obrigatório")
    private String titulo;

    private String descricao;
    private String videoUrl;
    private String materialUrl;
    private Integer ordem;

    @NotNull(message = "Curso é obrigatório")
    private Long cursoId;

    private String cursoTitulo;
    private LocalDateTime createdAt;
}
