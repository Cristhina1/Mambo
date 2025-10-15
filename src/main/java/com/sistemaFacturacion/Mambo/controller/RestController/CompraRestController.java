

package com.sistemaFacturacion.Mambo.controller.RestController;

import com.sistemaFacturacion.Mambo.model.Compra;
import com.sistemaFacturacion.Mambo.Repository.CompraRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/client")
public class CompraRestController {

    private final CompraRepository compraRepository;

    public CompraRestController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    // ✅ Guarda la compra enviada desde el carrito (JSON)
    @PostMapping("/guardarCompra")
    public String guardarCompra(@RequestBody Compra compra) {
        compra.setFecha(LocalDateTime.now());
        compraRepository.save(compra);
        return "Compra registrada correctamente";
    }
}
 
    

