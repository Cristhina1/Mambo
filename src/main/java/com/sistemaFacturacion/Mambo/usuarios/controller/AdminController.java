package com.sistemaFacturacion.Mambo.usuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String mostrarAdmin(Model model) {
        return "redirect:/admin/home"; // redirige a /admin/home
    }

    @GetMapping("/admin/principal")
    public String mostrarPaginaPrincipal(Model model) {
        return "admin/principal"; // vista para el panel de administración
    }
}