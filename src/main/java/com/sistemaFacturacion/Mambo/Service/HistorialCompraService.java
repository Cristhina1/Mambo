package com.sistemaFacturacion.Mambo.Service;

import com.sistemaFacturacion.Mambo.model.HistorialCompra;
import com.sistemaFacturacion.Mambo.Repository.HistorialCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialCompraService {

    private final HistorialCompraRepository historialCompraRepository;

    public HistorialCompraService(HistorialCompraRepository historialCompraRepository) {
        this.historialCompraRepository = historialCompraRepository;
    }

    public HistorialCompra guardar(HistorialCompra historialCompra) {
        return historialCompraRepository.save(historialCompra);
    }

    public List<HistorialCompra> obtenerPorCliente(Long clienteId) {
        return historialCompraRepository.findByClienteId(clienteId);
    }

    public List<HistorialCompra> listarTodo() {
        return historialCompraRepository.findAll();
    }
}
