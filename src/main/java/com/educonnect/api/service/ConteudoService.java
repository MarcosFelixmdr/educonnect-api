package com.educonnect.api.service;

import com.educonnect.api.dto.ConteudoDTO;
import com.educonnect.api.model.Conteudo;
import com.educonnect.api.model.Curso;
import com.educonnect.api.repository.ConteudoRepository;
import com.educonnect.api.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConteudoService {

    private final ConteudoRepository conteudoRepository;
    private final CursoRepository cursoRepository;

    public List<ConteudoDTO> listarPorCurso(Long cursoId) {
        return conteudoRepository.findByCursoIdOrderByOrdemAsc(cursoId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ConteudoDTO buscarPorId(Long id) {
        Conteudo conteudo = conteudoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado com id: " + id));
        return toDTO(conteudo);
    }

    public ConteudoDTO criar(ConteudoDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + dto.getCursoId()));

        Conteudo conteudo = Conteudo.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .videoUrl(dto.getVideoUrl())
                .ordem(dto.getOrdem() != null ? dto.getOrdem() : 0)
                .curso(curso)
                .build();

        return toDTO(conteudoRepository.save(conteudo));
    }

    public ConteudoDTO atualizar(Long id, ConteudoDTO dto) {
        Conteudo conteudo = conteudoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado com id: " + id));

        conteudo.setTitulo(dto.getTitulo());
        conteudo.setDescricao(dto.getDescricao());
        conteudo.setVideoUrl(dto.getVideoUrl());
        conteudo.setOrdem(dto.getOrdem() != null ? dto.getOrdem() : conteudo.getOrdem());

        return toDTO(conteudoRepository.save(conteudo));
    }

    public void deletar(Long id) {
        if (!conteudoRepository.existsById(id)) {
            throw new RuntimeException("Conteúdo não encontrado com id: " + id);
        }
        conteudoRepository.deleteById(id);
    }

    private ConteudoDTO toDTO(Conteudo conteudo) {
        return ConteudoDTO.builder()
                .id(conteudo.getId())
                .titulo(conteudo.getTitulo())
                .descricao(conteudo.getDescricao())
                .videoUrl(conteudo.getVideoUrl())
                .ordem(conteudo.getOrdem())
                .cursoId(conteudo.getCurso().getId())
                .cursoTitulo(conteudo.getCurso().getTitulo())
                .createdAt(conteudo.getCreatedAt())
                .build();
    }
}
