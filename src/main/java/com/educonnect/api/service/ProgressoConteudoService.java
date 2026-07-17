package com.educonnect.api.service;

import com.educonnect.api.model.ProgressoConteudo;
import com.educonnect.api.repository.ProgressoConteudoRepository;
import com.educonnect.api.repository.UsuarioRepository;
import com.educonnect.api.repository.ConteudoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressoConteudoService {

    private final ProgressoConteudoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;
    private final ConquistaService conquistaService;

    public List<Long> listarConteudosConcluidos(Long aprendizId) {
        return repository.findByAprendizId(aprendizId).stream()
                .filter(ProgressoConteudo::isConcluido)
                .map(ProgressoConteudo::getConteudoId)
                .collect(Collectors.toList());
    }

    public void alternarProgresso(Long aprendizId, Long conteudoId, boolean concluido) {
        Optional<ProgressoConteudo> existing = repository.findByAprendizIdAndConteudoId(aprendizId, conteudoId);
        boolean wasConcluido = false;
        
        if (existing.isPresent()) {
            wasConcluido = existing.get().isConcluido();
            ProgressoConteudo progresso = existing.get();
            progresso.setConcluido(concluido);
            repository.save(progresso);
        } else {
            ProgressoConteudo progresso = ProgressoConteudo.builder()
                    .aprendizId(aprendizId)
                    .conteudoId(conteudoId)
                    .concluido(concluido)
                    .build();
            repository.save(progresso);
        }

        if (concluido != wasConcluido) {
            usuarioRepository.findById(aprendizId).ifPresent(u -> {
                int newXp = u.getXp() + (concluido ? 10 : -10);
                if (newXp < 0) newXp = 0;
                u.setXp(newXp);
                usuarioRepository.save(u);
            });
        }

        if (concluido) {
            conteudoRepository.findById(conteudoId).ifPresent(cont -> {
                Long cursoId = cont.getCurso().getId();
                List<com.educonnect.api.model.Conteudo> totalContents = conteudoRepository.findByCursoIdOrderByOrdemAsc(cursoId);
                List<Long> completedIds = listarConteudosConcluidos(aprendizId);
                long completedInCourseCount = totalContents.stream().filter(c -> completedIds.contains(c.getId())).count();
                
                if (totalContents.size() > 0 && completedInCourseCount == totalContents.size()) {
                    try {
                        conquistaService.concederSeNaoExistir(
                                aprendizId,
                                "DISCIPLINADO",
                                "Estudante Disciplinado",
                                "Completou 100% dos conteúdos de um curso.",
                                "📚"
                        );
                    } catch (Exception e) {
                        System.err.println("Erro ao conceder conquista DISCIPLINADO: " + e.getMessage());
                    }
                }
            });
        }
    }
}
