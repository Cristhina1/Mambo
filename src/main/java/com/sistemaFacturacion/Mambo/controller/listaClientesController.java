package com.sistemaFacturacion.Mambo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class listaClientesController {
    
    @GetMapping("/admin/clientes")
    public String mostrarClientes(Model model) {
        model.addAttribute("seccionActiva", "clientes");
        return "admin/clientes";
    }
}
