package com.ejemplo.ForoHub.controlador;

import com.ejemplo.ForoHub.modelo.Usuario;
import com.ejemplo.ForoHub.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreado = usuarioServicio.crearUsuario(usuario);
        return ResponseEntity.created(URI.create("/api/auth/usuarios/" + usuarioCreado.getId())).body(usuarioCreado);
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestParam String nombreUsuario, @RequestParam String contrasena) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(401).body("Nombre de usuario o contraseña incorrectos");
        }
    }
}