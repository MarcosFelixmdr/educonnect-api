package com.educonnect.api.service;

import com.educonnect.api.dto.CursoDTO;
import com.educonnect.api.model.Curso;
import com.educonnect.api.model.Prova;
import com.educonnect.api.model.Usuario;
import com.educonnect.api.repository.CursoRepository;
import com.educonnect.api.repository.ProvaRepository;
import com.educonnect.api.repository.QuestaoRepository;
import com.educonnect.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProvaRepository provaRepository;
    private final QuestaoRepository questaoRepository;

    public List<CursoDTO> listarTodos() {
        return cursoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CursoDTO buscarPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + id));
        return toDTO(curso);
    }

    public CursoDTO criar(CursoDTO dto) {
        Usuario educador = usuarioRepository.findById(dto.getEducadorId())
                .orElseThrow(() -> new RuntimeException("Educador não encontrado com id: " + dto.getEducadorId()));

        if (educador.getTipo() != Usuario.TipoUsuario.EDUCADOR) {
            throw new RuntimeException("Usuário informado não é um educador.");
        }

        validarStatusAtivo(null, dto.getStatus());

        Curso curso = toEntity(dto, educador);
        if (dto.getStatus() == null) {
            curso.setStatus(Curso.StatusCurso.PENDENTE);
        }
        return toDTO(cursoRepository.save(curso));
    }

    public CursoDTO atualizar(Long id, CursoDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + id));

        Usuario educador = usuarioRepository.findById(dto.getEducadorId())
                .orElseThrow(() -> new RuntimeException("Educador não encontrado com id: " + dto.getEducadorId()));

        if (dto.getStatus() != null) {
            validarStatusAtivo(id, dto.getStatus());
        }

        curso.setTitulo(dto.getTitulo());
        curso.setDescricao(dto.getDescricao());
        curso.setCategoria(dto.getCategoria());
        curso.setCargaHoraria(dto.getCargaHoraria());
        curso.setNivel(dto.getNivel());
        curso.setEducador(educador);
        if (dto.getStatus() != null) {
            curso.setStatus(dto.getStatus());
        }

        return toDTO(cursoRepository.save(curso));
    }

    private void validarStatusAtivo(Long cursoId, Curso.StatusCurso status) {
        if (status == Curso.StatusCurso.ATIVO) {
            if (cursoId == null) {
                throw new RuntimeException("Um novo curso não pode ser ativado diretamente. Cadastre-o primeiro, crie a avaliação de 10 questões e então aprove-o.");
            }
            Prova prova = provaRepository.findByCursoId(cursoId)
                    .orElseThrow(() -> new RuntimeException("Não é possível ativar o curso: Nenhuma avaliação/prova foi criada para ele ainda."));
            long count = questaoRepository.countByProvaId(prova.getId());
            if (count != 10) {
                throw new RuntimeException("Não é possível ativar o curso: A avaliação precisa ter exatamente 10 questões cadastradas (atualmente possui: " + count + "/10).");
            }
        }
    }

    public void deletar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso não encontrado com id: " + id);
        }
        cursoRepository.deleteById(id);
    }

    private CursoDTO toDTO(Curso curso) {
        return CursoDTO.builder()
                .id(curso.getId())
                .titulo(curso.getTitulo())
                .descricao(curso.getDescricao())
                .categoria(curso.getCategoria())
                .cargaHoraria(curso.getCargaHoraria())
                .nivel(curso.getNivel())
                .educadorId(curso.getEducador().getId())
                .educadorNome(curso.getEducador().getNome())
                .status(curso.getStatus())
                .build();
    }

    private Curso toEntity(CursoDTO dto, Usuario educador) {
        return Curso.builder()
                .titulo(dto.getTitulo())
                .descricao(dto.getDescricao())
                .categoria(dto.getCategoria())
                .cargaHoraria(dto.getCargaHoraria())
                .nivel(dto.getNivel())
                .educador(educador)
                .status(dto.getStatus() != null ? dto.getStatus() : Curso.StatusCurso.ATIVO)
                .build();
    }
}
