package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.model.Comprobante;
import com.sistemaFacturacion.Mambo.service.ComprobanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    private final ComprobanteService comprobanteService;

    public ComprobanteController(ComprobanteService comprobanteService) {
        this.comprobanteService = comprobanteService;
    }

    // Crear comprobante
    @PostMapping
    public Comprobante crear(@RequestBody Comprobante comprobante) {
        return comprobanteService.crearComprobante(comprobante);
    }

    // Listar todos los comprobantes
    @GetMapping
    public List<Comprobante> listar() {
        return comprobanteService.listarComprobantes();
    }

    // Obtener comprobante por ID
    @GetMapping("/{id}")
    public Optional<Comprobante> obtener(@PathVariable Long id) {
        return comprobanteService.obtenerPorId(id);
    }

    // Buscar comprobantes por tipo (ejemplo: FACTURA o BOLETA)
    @GetMapping("/buscar/tipo/{tipo}")
    public List<Comprobante> buscarPorTipo(@PathVariable String tipo) {
        return comprobanteService.buscarPorTipo(tipo);
    }

    // Buscar comprobantes por número de documento
    @GetMapping("/buscar/documento/{numero}")
    public List<Comprobante> buscarPorDocumento(@PathVariable String numero) {
        return comprobanteService.buscarPorNumeroDocumento(numero);
    }

    // Actualizar comprobante
    @PutMapping("/{id}")
    public Comprobante actualizar(@PathVariable Long id, @RequestBody Comprobante comprobante) {
        return comprobanteService.actualizarComprobante(id, comprobante);
    }

    // Eliminar comprobante
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        comprobanteService.eliminarComprobante(id);
        return "Comprobante eliminado con ID: " + id;
    }
}

