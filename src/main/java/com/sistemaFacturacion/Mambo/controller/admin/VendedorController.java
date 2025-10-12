package com.sistemaFacturacion.Mambo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sistemaFacturacion.Mambo.Service.VendedorService;
import com.sistemaFacturacion.Mambo.model.Vendedor;

@Controller
@RequestMapping("/admin/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public String listaVendedores(Model model) {
        var vendedores = vendedorService.listar();

        int cantVendedores = vendedores.size();

        model.addAttribute("vendedores", vendedores);
        model.addAttribute("cantVendedores", cantVendedores);
        model.addAttribute("vendedor", new Vendedor());

        return "admin/vendedores"; 
    }

    @PostMapping("guardar")
    public String guardarVendedor(@ModelAttribute Vendedor vendedor) {
        vendedorService.guardar(vendedor);
        return "redirect:/admin/vendedores";
    }

    @PostMapping("eliminar/{id}")
    public String eliminarVendedor(@PathVariable Long id) {
        vendedorService.eliminar(id);
        return "redirect:/admin/vendedores";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Vendedor obtenerVendedor(@PathVariable Long id) {
        return vendedorService.buscar(id);
    }

    @GetMapping("/filtrar")
    @ResponseBody
    public List<Vendedor> filtrar(@RequestParam String buscar) {
    String esto = buscar.toLowerCase();
    return vendedorService.listar().stream()
            .filter(v -> v.getId().toString().contains(esto) ||
                         v.getNombre().toLowerCase().contains(esto) ||
                         v.getEmail().toLowerCase().contains(esto))
            .toList();
    }


}

