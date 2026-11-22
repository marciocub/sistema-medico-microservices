package com.sistema.seguridad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.sistema.seguridad"
})
@EnableJpaRepositories(basePackages = {
    "com.sistema.seguridad.repository"
})
@EntityScan(basePackages = {
    "com.sistema.seguridad.entity"
})
public class SeguridadMedicoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeguridadMedicoApplication.class, args);
    }
}
