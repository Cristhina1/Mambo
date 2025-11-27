package com.sistemaFacturacion.Mambo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.entity.Repository.PagoRepository;
import com.sistemaFacturacion.Mambo.entity.model.TipoPago;
import com.sistemaFacturacion.Mambo.entity.model.pago;
import com.sistemaFacturacion.Mambo.mape.dto.PagoDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.PagoMape;


@Service
public class PagoService {
    @Autowired
    private PagoMape pagoMape;

    @Autowired
    private PagoRepository pagoRepository;
    
    public PagoDTO guardar(PagoDTO pagoDTO) {
        pago pago = pagoMape.toEntity(pagoDTO);
        pago nuevo = pagoRepository.save(pago);
        return pagoMape.toDto(nuevo);
    }
    
    public List<PagoDTO> listar(){
        List<pago> pagos = pagoRepository.findAll();
        return pagos.stream().map(pagoMape::toDto).toList();
    }

    public PagoDTO actualizar(Long id, PagoDTO pagoDTO) {
        pago existente = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        existente.setPrecio(pagoDTO.getPrecio());
        existente.setTipoPago(TipoPago.valueOf(pagoDTO.getTipoPago()));
        existente.setFechaPago(pagoDTO.getFechaPago());
        pago actualizado = pagoRepository.save(existente);
        return pagoMape.toDto(actualizado);
    }
}