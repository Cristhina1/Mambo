package com.sistemaFacturacion.Mambo.client.controller;

import com.sistemaFacturacion.Mambo.client.service.ClienteService;
import com.sistemaFacturacion.Mambo.model.cliente;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Crear cliente
    @PostMapping
    public cliente crear(@RequestBody cliente c) {
        return clienteService.crearCliente(c);
    }

    // Listar todos los clientes
    @GetMapping
    public List<cliente> listar() {
        return clienteService.listarClientes();
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public Optional<cliente> obtener(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }

    // Buscar cliente por email
    @GetMapping("/buscar/email/{email}")
    public Optional<cliente> buscarPorEmail(@PathVariable String email) {
        return clienteService.buscarPorEmail(email);
    }

    // Buscar cliente por número de documento
    @GetMapping("/buscar/documento/{numero}")
    public Optional<cliente> buscarPorDocumento(@PathVariable String numero) {
        return clienteService.buscarPorNumeroDocumento(numero);
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public cliente actualizar(@PathVariable Long id, @RequestBody cliente c) {
        return clienteService.actualizarCliente(id, c);
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "Cliente eliminado con ID: " + id;
    }
}
