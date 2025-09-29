package com.sistemaFacturacion.Mambo.client.service;

import com.sistemaFacturacion.Mambo.client.repository.ClienteRepository;
import com.sistemaFacturacion.Mambo.model.cliente;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Inyección de dependencias por constructor
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Crear cliente
    public cliente crearCliente(cliente c) {
        return clienteRepository.save(c);
    }

    // Actualizar cliente
    public cliente actualizarCliente(Long id, cliente c) {
        return clienteRepository.findById(id).map(existing -> {
            existing.setNombreCompleto(c.getNombreCompleto());
            existing.setTipoDocumento(c.getTipoDocumento());
            existing.setNumeroDocumento(c.getNumeroDocumento());
            existing.setEmail(c.getEmail());
            existing.setTelefono(c.getTelefono());
            existing.setContra(c.getContra());
            return clienteRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    // Eliminar cliente
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Obtener cliente por ID
    public Optional<cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Listar todos los clientes
    public List<cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por email
    public Optional<cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    // Buscar cliente por número de documento
    public Optional<cliente> buscarPorNumeroDocumento(String numeroDocumento) {
        return clienteRepository.findByNumeroDocumento(numeroDocumento);
    }
}
