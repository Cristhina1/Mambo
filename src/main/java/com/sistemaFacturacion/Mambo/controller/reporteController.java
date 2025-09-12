package com.sistemaFacturacion.Mambo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class reporteController {

    @GetMapping("/admin/reporte")
    public String mostrarReporte(Model model) {
        model.addAttribute("seccionActiva", "reporte");
        return "admin/reporte";
    }
}
