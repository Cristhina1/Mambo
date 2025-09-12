package com.sistemaFacturacion.Mambo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class vendedoresController {
    @GetMapping("/admin/vendedores")
    public String mostrarVendedores(Model model) {
        model.addAttribute("seccionActiva", "vendedores");
        return "admin/vendedores";
    }
}
