package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.model.rol;
import com.sistemaFacturacion.Mambo.service.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // Listar todos los roles
    @GetMapping
    @ResponseBody
    public List<rol> listarRoles() {
        return rolService.listarRoles();
    }

    // Obtener rol por ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<rol> obtenerRolPorId(@PathVariable Long id) {
        return rolService.obtenerRolPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear rol
    @PostMapping
    @ResponseBody
    public rol crearRol(@RequestBody rol rol) {
        return rolService.crearRol(rol);
    }

    // Actualizar rol
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<rol> actualizarRol(@PathVariable Long id, @RequestBody rol rolActualizado) {
        try {
            return ResponseEntity.ok(rolService.actualizarRol(id, rolActualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar rol
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}
