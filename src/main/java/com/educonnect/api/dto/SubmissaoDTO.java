package com.educonnect.api.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SubmissaoDTO {
    private Long provaId;
    private Long aprendizId;
    /**
     * Key: questaoId, Value: resposta do aluno ("A", "B", "C" ou "D")
     */
    private Map<Long, String> respostas;
}
