package com.sistema.orquestador.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "services")
public class MicroserviceConfig {

    private String seguridadUrl = "http://localhost:8081";
    private String pacientesUrl = "http://localhost:8082";
    private String profesionalesUrl = "http://localhost:8083";
    private String turnosUrl = "http://localhost:8084";

    public String getSeguridadUrl() {
        return seguridadUrl;
    }

    public void setSeguridadUrl(String seguridadUrl) {
        this.seguridadUrl = seguridadUrl;
    }

    public String getPacientesUrl() {
        return pacientesUrl;
    }

    public void setPacientesUrl(String pacientesUrl) {
        this.pacientesUrl = pacientesUrl;
    }

    public String getProfesionalesUrl() {
        return profesionalesUrl;
    }

    public void setProfesionalesUrl(String profesionalesUrl) {
        this.profesionalesUrl = profesionalesUrl;
    }

    public String getTurnosUrl() {
        return turnosUrl;
    }

    public void setTurnosUrl(String turnosUrl) {
        this.turnosUrl = turnosUrl;
    }
}
