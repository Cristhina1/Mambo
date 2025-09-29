package com.sistemaFacturacion.Mambo.dashboard.service;

import com.sistemaFacturacion.Mambo.carrito.repository.CarritoRepository;
import com.sistemaFacturacion.Mambo.client.repository.ClienteRepository;
import com.sistemaFacturacion.Mambo.dashboard.repository.ComprobanteRepository;
import com.sistemaFacturacion.Mambo.model.Comprobante;
import com.sistemaFacturacion.Mambo.model.carrito;
import com.sistemaFacturacion.Mambo.model.cliente;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final CarritoRepository carritoRepository;
    private final ClienteRepository ClienteRepository;

    public ComprobanteService(ComprobanteRepository comprobanteRepository,
                              CarritoRepository carritoRepository,
                              ClienteRepository clienteRepository) {
        this.comprobanteRepository = comprobanteRepository;
        this.carritoRepository = carritoRepository;
        this.ClienteRepository = clienteRepository;
    }

    // Crear comprobante con datos automáticos
    public Comprobante crearComprobante(Comprobante comprobante) {
        // Buscar carrito
        carrito carrito = carritoRepository.findById(comprobante.getCarrito().getId())
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Obtener cliente desde el carrito
        cliente cliente = carrito.getCliente();
        if (cliente == null) {
            throw new RuntimeException("El carrito no tiene cliente asociado");
        }

        // Copiar datos del cliente (histórico)
        comprobante.setCliente(cliente);
        comprobante.setNombreCliente(cliente.getNombreCompleto());
        comprobante.setTipoDocumento(cliente.getTipoDocumento().getNombre()); // asumiendo que tipoDocumento tiene campo nombre
        comprobante.setNumeroDocumento(cliente.getNumeroDocumento());

        // Calcular el total desde el carrito
        comprobante.setTotal(carrito.getTotal());

        return comprobanteRepository.save(comprobante);
    }

    // Actualizar comprobante (solo cambia tipo, no cliente ni total)
    public Comprobante actualizarComprobante(Long id, Comprobante comprobante) {
        return comprobanteRepository.findById(id).map(existing -> {
            existing.setTipo(comprobante.getTipo());
            return comprobanteRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Comprobante no encontrado con ID: " + id));
    }

    public void eliminarComprobante(Long id) {
        comprobanteRepository.deleteById(id);
    }

    public Optional<Comprobante> obtenerPorId(Long id) {
        return comprobanteRepository.findById(id);
    }

    public List<Comprobante> listarComprobantes() {
        return comprobanteRepository.findAll();
    }

    public List<Comprobante> buscarPorTipo(String tipo) {
        return comprobanteRepository.findByTipo(tipo);
    }

    public List<Comprobante> buscarPorNumeroDocumento(String numeroDocumento) {
        return comprobanteRepository.findByNumeroDocumento(numeroDocumento);
    }
}
