package com.educonnect.api.dto;

import lombok.Data;

import java.util.Map;

@Data
public class SubmissaoDTO {
    private Long provaId;
    private Long aprendizId;
    
    private Map<Long, String> respostas;
}
