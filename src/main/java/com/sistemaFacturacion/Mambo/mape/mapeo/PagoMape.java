package com.sistemaFacturacion.Mambo.mape.mapeo;

import java.time.LocalDateTime;
import com.sistemaFacturacion.Mambo.entity.model.TipoPago;
import com.sistemaFacturacion.Mambo.entity.model.pago;
import com.sistemaFacturacion.Mambo.mape.dto.PagoDTO;

public class PagoMape {
    public pago toEntity(PagoDTO dto){
        pago pago = new pago();
        pago.setPrecio(dto.getPrecio());
        pago.setFechaPago(dto.getFechaPago() != null ? dto.getFechaPago() : LocalDateTime.now());
        pago.setTipoPago(TipoPago.valueOf(dto.getTipoPago()));

        return pago;
    }

    public PagoDTO toDto(pago pago) {
    PagoDTO dto = new PagoDTO();
    dto.setId(pago.getId());
    dto.setPrecio(pago.getPrecio());
    dto.setTipoPago(pago.getTipoPago().name());
    dto.setFechaPago(pago.getFechaPago());
    return dto;
}

}
