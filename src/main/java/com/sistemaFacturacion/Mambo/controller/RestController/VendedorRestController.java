package com.sistemaFacturacion.Mambo.controller.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistemaFacturacion.Mambo.Service.VendedorService;
import com.sistemaFacturacion.Mambo.model.Vendedor;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorRestController {
    private final VendedorService vendedorService;

    public VendedorRestController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public List<Vendedor> listar() {
        return vendedorService.listar();
    }

    @GetMapping("/{id}")
    public Vendedor obtener(@PathVariable Long id) {
        return vendedorService.buscar(id);
    }

    @PostMapping
    public Vendedor crear(@RequestBody Vendedor v) {
        return vendedorService.guardar(v);
    }

    @PutMapping("/{id}")
    public Vendedor actualizar(@PathVariable Long id, @RequestBody Vendedor v) {
        Vendedor existente = vendedorService.buscar(id);
        if (existente != null) {
            existente.setNombre(v.getNombre());
            existente.setEmail(v.getEmail());
            existente.setSalario(v.getSalario());
            return vendedorService.guardar(existente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        vendedorService.eliminar(id);
    }
}
