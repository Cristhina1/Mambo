package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.mape.dto.PagoDTO;
import com.sistemaFacturacion.Mambo.Service.PagoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> Listar() {
        return ResponseEntity.ok(pagoService.listar());
    }
}