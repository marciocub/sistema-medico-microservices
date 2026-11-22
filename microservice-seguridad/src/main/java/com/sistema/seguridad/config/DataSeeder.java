package com.sistema.seguridad.config;

import com.sistema.seguridad.entity.UsuarioEntity;
import com.sistema.seguridad.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initAdminUser(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            // Check if admin user exists by trying to find it
            boolean adminExists = usuarioRepository.findByUsername("admin").isPresent();
            if (!adminExists) {
                UsuarioEntity admin = UsuarioEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .rol("ADMIN")
                        .enabled(true)
                        .build();
                usuarioRepository.save(admin);
                System.out.println(">>> Usuario admin creado: username=admin, password=admin123");
            }
        };
    }
}
