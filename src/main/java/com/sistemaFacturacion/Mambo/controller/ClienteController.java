package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.model.cliente;
import com.sistemaFacturacion.Mambo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Controller
@RequestMapping("/admin/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("cliente", new cliente());
        return "admin/clientes";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/admin/clientes";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<cliente> obtenerCliente(@PathVariable Long id) {
        cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente != null) return ResponseEntity.ok(cliente);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/admin/clientes";
    }

    // Endpoint AJAX para filtrar
    @GetMapping("/filtrar")
    @ResponseBody
    public List<cliente> filtrarClientes(@RequestParam(required = false) String buscar,
                                        @RequestParam(required = false) String tipo,
                                        @RequestParam(required = false) String estado) {
        return clienteService.filtrarClientes(buscar, tipo, estado);
    }
}
