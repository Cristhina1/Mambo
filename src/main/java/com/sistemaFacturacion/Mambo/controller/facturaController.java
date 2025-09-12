package com.sistemaFacturacion.Mambo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class facturaController {
    @GetMapping("/admin/factura")
    public String mostrarFactura(Model model) {
        model.addAttribute("seccionActiva", "factura");
        return "admin/factura";
    }
}
