package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.Service.ClienteService;
import com.sistemaFacturacion.Mambo.entity.model.cliente;
import com.sistemaFacturacion.Mambo.mape.dto.ClienteDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.ClienteMape;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
    
    private final ClienteService clienteService;
    private final ClienteMape clienteMape;

    public ClienteRestController(ClienteService clienteService,ClienteMape clienteMape) {
        this.clienteService = clienteService;
        this.clienteMape = clienteMape;
    }

    // Crear cliente


    // Listar todos los clientes
    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listarClientes();
    }

    // Obtener cliente por ID
    @GetMapping("/buscar/{id}")
    public Optional<ClienteDTO> obtener(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }



    // Actualizar cliente
    @PutMapping("/actualizar/{id}")
    public ClienteDTO actualizar(@PathVariable Long id, @RequestBody ClienteDTO c) {
        return clienteService.actualizarCliente(id, c);
    }

    // Eliminar cliente
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "Cliente eliminado con ID: " + id;
    }

    @PostMapping
public ClienteDTO crear(@RequestBody ClienteDTO clienteDTO) {
    cliente c = clienteService.crearCliente(clienteDTO);
    return clienteMape.toDto(c);
}
}

