package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.dto.ProductoDTO;
import com.sistemaFacturacion.Mambo.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/productos")
public class ProductosController { // ✅ con mayúscula

    private final ProductoService productoService;

    public ProductosController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("producto", new ProductoDTO());
        return "admin/productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") ProductoDTO productoDTO) {
        productoService.guardarProducto(productoDTO);
        return "redirect:/admin/productos";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
    productoService.eliminarProducto(id);
    return "redirect:/admin/productos";
}

}
