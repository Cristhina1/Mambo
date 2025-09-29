package com.sistemaFacturacion.Mambo.usuarios.controller;

import com.sistemaFacturacion.Mambo.model.Usuario;
import com.sistemaFacturacion.Mambo.usuarios.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 📌 Este controller responde con JSON
@RequestMapping("/usuarios") // 📌 Ruta base
public class UsuarioController {

    private final UsuarioService usuarioService;

    // 📌 Inyección del servicio en el constructor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 📌 GET → Listar todos los usuarios
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarTodos();
    }

    // 📌 GET → Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 📌 POST → Crear un nuevo usuario
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    // 📌 PUT → Actualizar usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.buscarPorId(id)
                .map(existente -> {
                    existente.setNombreCompleto(usuario.getNombreCompleto());
                    existente.setRol(usuario.getRol());
                    existente.setNumeroDocumento(usuario.getNumeroDocumento());
                    existente.setEmail(usuario.getEmail());
                    existente.setTelefono(usuario.getTelefono());
                    existente.setContra(usuario.getContra()); // ⚠️ Aquí deberías cifrar contraseña con BCrypt
                    return ResponseEntity.ok(usuarioService.guardar(existente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 📌 DELETE → Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (usuarioService.buscarPorId(id).isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
