package com.sistemaFacturacion.Mambo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class boletaController {
    @GetMapping("/admin/boleta")
    public String mostrarBoleta(Model model) {
        model.addAttribute("seccionActiva", "boleta");
        return "admin/boleta";
    }
}
