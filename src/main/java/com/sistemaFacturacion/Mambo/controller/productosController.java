package com.sistemaFacturacion.Mambo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class productosController {

    @GetMapping("/admin/productos")
    public String mostrarProductos(Model model) {
        model.addAttribute("seccionActiva", "productos");
        return "admin/productos";
    }
}
