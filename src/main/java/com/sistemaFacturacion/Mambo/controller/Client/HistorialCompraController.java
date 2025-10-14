package com.sistemaFacturacion.Mambo.controller.Client;

import com.sistemaFacturacion.Mambo.Service.HistorialCompraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class HistorialCompraController {

    private final HistorialCompraService historialCompraService;

    public HistorialCompraController(HistorialCompraService historialCompraService) {
        this.historialCompraService = historialCompraService;
    }

    @GetMapping("/historial")
    public String mostrarHistorial(Model model) {
        Long clienteId = 1L; // temporal, luego se obtendrá del usuario logueado
        model.addAttribute("compras", historialCompraService.obtenerPorCliente(clienteId));
        return "client/fragments/historial-compras";
    }
}
