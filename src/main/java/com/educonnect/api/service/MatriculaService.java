package com.educonnect.api.service;

import com.educonnect.api.dto.MatriculaDTO;
import com.educonnect.api.model.Curso;
import com.educonnect.api.model.Matricula;
import com.educonnect.api.model.Usuario;
import com.educonnect.api.repository.CursoRepository;
import com.educonnect.api.repository.MatriculaRepository;
import com.educonnect.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final ConquistaService conquistaService;

    public List<MatriculaDTO> listarTodos() {
        return matriculaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public MatriculaDTO buscarPorId(Long id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada com id: " + id));
        return toDTO(matricula);
    }

    public MatriculaDTO criar(MatriculaDTO dto) {
        Usuario aprendiz = usuarioRepository.findById(dto.getAprendizId())
                .orElseThrow(() -> new RuntimeException("Aprendiz não encontrado com id: " + dto.getAprendizId()));

        if (aprendiz.getTipo() != Usuario.TipoUsuario.APRENDIZ) {
            throw new RuntimeException("Usuário informado não é um aprendiz.");
        }

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + dto.getCursoId()));

        Matricula matricula = Matricula.builder()
                .aprendiz(aprendiz)
                .curso(curso)
                .dataInscricao(LocalDate.now())
                .status(Matricula.StatusMatricula.PENDENTE)
                .build();

        Matricula saved = matriculaRepository.save(matricula);
        
        try {
            conquistaService.concederSeNaoExistir(
                    aprendiz.getId(),
                    "PRIMEIRO_PASSO",
                    "Primeiro Passo",
                    "Realizou sua matrícula em um curso da plataforma.",
                    "🎒"
            );
        } catch (Exception e) {
            System.err.println("Erro ao conceder conquista PRIMEIRO_PASSO: " + e.getMessage());
        }

        return toDTO(saved);
    }

    public MatriculaDTO atualizar(Long id, MatriculaDTO dto) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada com id: " + id));

        if (dto.getStatus() != null) {
            matricula.setStatus(dto.getStatus());
            
            if (dto.getStatus() == Matricula.StatusMatricula.CONCLUIDA) {
                if (matricula.getCertificadoHash() == null || matricula.getCertificadoHash().isEmpty()) {
                    matricula.setCertificadoHash(java.util.UUID.randomUUID().toString());
                }
                try {
                    conquistaService.concederSeNaoExistir(
                            matricula.getAprendiz().getId(),
                            "CONCLUDENTE",
                            "Formado!",
                            "Concluiu seu primeiro curso e emitiu o certificado.",
                            "🎓"
                    );
                } catch (Exception e) {
                    System.err.println("Erro ao conceder conquista CONCLUDENTE: " + e.getMessage());
                }
            }
        }

        if (dto.getCursoId() != null) {
            Curso curso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + dto.getCursoId()));
            matricula.setCurso(curso);
        }

        return toDTO(matriculaRepository.save(matricula));
    }

    public void deletar(Long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new RuntimeException("Matrícula não encontrada com id: " + id);
        }
        matriculaRepository.deleteById(id);
    }

    public MatriculaDTO buscarPorCertificadoHash(String hash) {
        Matricula matricula = matriculaRepository.findByCertificadoHash(hash)
                .orElseThrow(() -> new RuntimeException("Certificado não encontrado com o hash informado."));
        return toDTO(matricula);
    }


    private MatriculaDTO toDTO(Matricula matricula) {
        return MatriculaDTO.builder()
                .id(matricula.getId())
                .aprendizId(matricula.getAprendiz().getId())
                .aprendizNome(matricula.getAprendiz().getNome())
                .cursoId(matricula.getCurso().getId())
                .cursoTitulo(matricula.getCurso().getTitulo())
                .dataInscricao(matricula.getDataInscricao())
                .status(matricula.getStatus())
                .certificadoHash(matricula.getCertificadoHash())
                .createdAt(matricula.getCreatedAt())
                .build();
    }
}

