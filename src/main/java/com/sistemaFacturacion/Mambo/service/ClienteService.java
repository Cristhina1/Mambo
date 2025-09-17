package com.sistemaFacturacion.Mambo.service;

import com.sistemaFacturacion.Mambo.model.Cliente;
import com.sistemaFacturacion.Mambo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Filtrado dinámico
    public List<Cliente> filtrarClientes(String buscar, String tipo, String estado) {
        return clienteRepository.findAll().stream().filter(c -> {
            boolean coincide = true;
            if (buscar != null && !buscar.isEmpty()) {
                String lowerBuscar = buscar.toLowerCase();
                coincide &= c.getNombreCompleto().toLowerCase().contains(lowerBuscar) || 
                           c.getNumeroDocumento().toLowerCase().contains(lowerBuscar);
            }
            if (tipo != null && !tipo.isEmpty()) {
                coincide &= c.getTipoDocumento().equalsIgnoreCase(tipo);
            }
            if (estado != null && !estado.isEmpty()) {
                coincide &= c.getEstado().equalsIgnoreCase(estado);
            }
            return coincide;
        }).toList();
    }
}
