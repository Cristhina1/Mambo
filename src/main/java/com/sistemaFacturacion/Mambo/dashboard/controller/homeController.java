package com.sistemaFacturacion.Mambo.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/admin/home")
    public String mostrarHome(Model model) {
        model.addAttribute("seccionActiva", "home");
        model.addAttribute("ventasDelDia", "S/ 2,450.00");
        model.addAttribute("clientesActivos", "127");
        model.addAttribute("productosStock", "89");
        model.addAttribute("ventasDelMes", "S/ 15,670.00");
        return "admin/home";
    }
}
