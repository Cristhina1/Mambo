package com.sistemaFacturacion.Mambo.vendedor.controller;

import com.sistemaFacturacion.Mambo.model.Vendedor;
import com.sistemaFacturacion.Mambo.vendedor.service.VendedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    // Listar todos los vendedores
    @GetMapping
    @ResponseBody
    public List<Vendedor> listarVendedores() {
        return vendedorService.listarVendedores();
    }

    // Obtener vendedor por ID
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Vendedor> obtenerPorId(@PathVariable Long id) {
        return vendedorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo vendedor
    @PostMapping
    @ResponseBody
    public Vendedor crearVendedor(@RequestBody Vendedor vendedor) {
        return vendedorService.crearVendedor(vendedor);
    }

    // Actualizar vendedor
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Vendedor> actualizarVendedor(@PathVariable Long id, @RequestBody Vendedor vendedorActualizado) {
        try {
            return ResponseEntity.ok(vendedorService.actualizarVendedor(id, vendedorActualizado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar vendedor
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> eliminarVendedor(@PathVariable Long id) {
        vendedorService.eliminarVendedor(id);
        return ResponseEntity.noContent().build();
    }
}
