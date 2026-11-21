package com.sistema.seguridad;

import com.sistema.seguridad.dto.UsuarioRegistroRequest;
import com.sistema.seguridad.dto.UsuarioResponse;
import com.sistema.seguridad.dto.LoginRequest;
import com.sistema.seguridad.dto.AuthResponse;

public interface SeguridadApi {
    UsuarioResponse registrarUsuario(UsuarioRegistroRequest request);
    AuthResponse autenticar(LoginRequest request);
    UsuarioResponse obtenerUsuarioPorUsername(String username);
    boolean existeUsuario(String username);
    UsuarioResponse asignarRol(String username, String rol);
}
