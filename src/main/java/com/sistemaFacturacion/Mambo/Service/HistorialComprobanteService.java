package com.sistemaFacturacion.Mambo.Service;

import com.sistemaFacturacion.Mambo.Repository.ComprobanteRepository;
import com.sistemaFacturacion.Mambo.dto.HistorialComprobanteDTO;
import com.sistemaFacturacion.Mambo.model.Comprobante;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistorialComprobanteService {

    private final ComprobanteRepository comprobanteRepository;

    public HistorialComprobanteService(ComprobanteRepository comprobanteRepository) {
        this.comprobanteRepository = comprobanteRepository;
    }

    // Listar historial de comprobantes de un cliente
    public List<HistorialComprobanteDTO> listarPorCliente(Long clienteId) {
        List<Comprobante> comprobantes = comprobanteRepository.findByClienteId(clienteId);

        return comprobantes.stream().map(c -> {
            HistorialComprobanteDTO dto = new HistorialComprobanteDTO();
            dto.setId(c.getId());
            dto.setClienteNombre(c.getNombreCliente());
            dto.setTipoDocumento(c.getTipoDocumento());
            dto.setNumeroDocumento(c.getNumeroDocumento());
            dto.setTipoComprobante(c.getTipo());
            dto.setFecha(c.getFechaCreacion());
            dto.setTotal(c.getTotal());
            return dto;
        }).collect(Collectors.toList());
    }
}
 