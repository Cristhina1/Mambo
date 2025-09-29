package com.sistemaFacturacion.Mambo.producto.controller;

import com.sistemaFacturacion.Mambo.dto.ProductoDTO;
import com.sistemaFacturacion.Mambo.producto.service.CategoriaService;
import com.sistemaFacturacion.Mambo.producto.service.ProductoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/productos")
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
        var producto = productoService.findAll();
        var categoria = categoriaService.listarCategorias();
        
        //contar la cantidad de productos
        int totalProductos = producto.size();
        //contar la cantidad de categoria
        int totalCategorias = categoria.size();

        //Cantidad de dinero en almacen
        double totalDinero = producto.stream()
                .mapToDouble(p -> p.getPrecio() * p.getStock())
                .sum();

        model.addAttribute("totalDinero", totalDinero);
        model.addAttribute("totalCategoria", totalCategorias);
        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("productoDTO", new ProductoDTO()); 
        return "admin/productos"; 
    }

    // crear producto
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute ProductoDTO productoDTO) {
        productoService.save(productoDTO);
        return "redirect:/admin/productos";
    }

    // Actualizar producto
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        ProductoDTO productoDTO = productoService.findDtoById(id);
        model.addAttribute("productoDTO", productoDTO);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "/admin/productos/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute ProductoDTO productoDTO) {
        productoService.actualizar(id, productoDTO);
        return "redirect:/admin/productos";
    }

    // ✅ Eliminar producto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.deleteById(id);
        return "redirect:/admin/productos";
    }

}
