package com.sistemaFacturacion.Mambo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sistemaFacturacion.Mambo.Service.ClienteService;
import com.sistemaFacturacion.Mambo.Service.ProductoService;

@Controller
public class Dashboard {
      @Autowired
        ClienteService clienteService;

        @Autowired
        ProductoService productoService;

    @GetMapping("/admin/home")
    public String mostrarHome(Model model) {
                var cliente = clienteService.listarClientes();
        var producto = productoService.findAll();
        var numCliente = cliente.size();
        var numProductos = producto.size();
        
        model.addAttribute("cantClientes", numCliente);
        model.addAttribute("cantProductos", numProductos);

        model.addAttribute("seccionActiva", "home");
        model.addAttribute("ventasDelDia", "S/ 2,450.00");
        model.addAttribute("ventasDelMes", "S/ 15,670.00");
        return "admin/home";
    }
}
