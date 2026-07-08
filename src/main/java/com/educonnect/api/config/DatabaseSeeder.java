package com.educonnect.api.config;

import com.educonnect.api.model.Usuario;
import com.educonnect.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        try {
            boolean adminExists = usuarioRepository.existsByEmail("adm");
            if (!adminExists) {
                Usuario admin = Usuario.builder()
                        .nome("Administrador")
                        .email("adm")
                        .tipo(Usuario.TipoUsuario.ADMIN)
                        .senha("admteste123")
                        .bio("Perfil administrador do sistema EduConnect.")
                        .areaAtuacao("Administração Geral")
                        .build();
                usuarioRepository.save(admin);
                System.out.println(">>> Admin criado via Seeder: login=adm / senha=admteste123");
            } else {
                System.out.println(">>> Admin ja existe no banco de dados.");
            }
        } catch (Exception e) {
            System.err.println(">>> Aviso: Seeder nao executou: " + e.getMessage());
            System.err.println(">>> O admin sera criado via data.sql automaticamente.");
        }
    }
}

