package com.sistemaFacturacion.Mambo.producto.controller;

import com.sistemaFacturacion.Mambo.dto.ProductoDTO;
import com.sistemaFacturacion.Mambo.producto.service.CategoriaService;
import com.sistemaFacturacion.Mambo.producto.service.ProductoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    // 🔹 Inyección por constructor
    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    // Lista de producto
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("productoDTO", new ProductoDTO());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "listadeproductos";
    }

    // crear producto
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductoDTO productoDTO) {
        productoService.save(productoDTO);
        return "redirect:/productos";
    }

    // Actualizar producto
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ProductoDTO productoDTO = productoService.findDtoById(id);
        model.addAttribute("productoDTO", productoDTO);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute ProductoDTO productoDTO) {
        productoService.actualizar(id, productoDTO);
        return "redirect:/productos";
    }

    // ✅ Eliminar producto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return "redirect:/productos";
    }

}
