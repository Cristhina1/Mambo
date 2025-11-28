package com.sistemaFacturacion.Mambo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaFacturacion.Mambo.Service.UsuarioService;
import com.sistemaFacturacion.Mambo.entity.model.Usuario;
import com.sistemaFacturacion.Mambo.mape.dto.VendedorDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // Crear un nuevo usuario (vendedor/admin)
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody VendedorDTO dto) {
        Usuario usuarioCreado = usuarioService.register(dto);
        return ResponseEntity.ok(usuarioCreado);
    }

    // Listar usuarios
    @GetMapping
    public ResponseEntity<List<VendedorDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listar());
    }
}

