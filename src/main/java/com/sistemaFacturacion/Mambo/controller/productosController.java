package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.dto.ProductoDTO;
import com.sistemaFacturacion.Mambo.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/productos")
public class productosController { // ✅ con mayúscula

    private final ProductoService ProductoService;

    public productosController(ProductoService productoService) {
        this.ProductoService = productoService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", ProductoService.listarProductos());
        model.addAttribute("producto", new ProductoDTO());
        return "admin/productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") ProductoDTO productoDTO) {
        ProductoService.guardarProducto(productoDTO);
        return "redirect:/admin/productos";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
    ProductoService.eliminarProducto(id);
    return "redirect:/admin/productos";
}

}
