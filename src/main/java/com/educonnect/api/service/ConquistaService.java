package com.educonnect.api.service;

import com.educonnect.api.model.Conquista;
import com.educonnect.api.repository.ConquistaRepository;
import com.educonnect.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConquistaService {

    private final ConquistaRepository repository;
    private final UsuarioRepository usuarioRepository;

    public List<Conquista> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public void concederSeNaoExistir(Long usuarioId, String tipo, String titulo, String descricao, String icone) {
        if (!repository.existsByUsuarioIdAndTipo(usuarioId, tipo)) {
            Conquista conquista = Conquista.builder()
                    .usuarioId(usuarioId)
                    .tipo(tipo)
                    .titulo(titulo)
                    .descricao(descricao)
                    .icone(icone)
                    .build();
            repository.save(conquista);

            int xpBono = 50;
            if ("DISCIPLINADO".equalsIgnoreCase(tipo)) xpBono = 100;
            else if ("GENIO".equalsIgnoreCase(tipo)) xpBono = 200;
            else if ("CONCLUDENTE".equalsIgnoreCase(tipo)) xpBono = 300;

            final int bonus = xpBono;
            usuarioRepository.findById(usuarioId).ifPresent(u -> {
                u.setXp(u.getXp() + bonus);
                usuarioRepository.save(u);
            });
        }
    }
}

