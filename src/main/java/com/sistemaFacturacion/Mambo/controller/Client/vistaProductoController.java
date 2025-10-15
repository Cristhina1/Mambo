package com.sistemaFacturacion.Mambo.controller.Client;

import com.sistemaFacturacion.Mambo.Service.ProductoService;
import com.sistemaFacturacion.Mambo.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class vistaProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // ✅ Muestra la lista de productos para el cliente
    @GetMapping("/listadeproductos")
    public String listarProductosCliente(Model model) {
        var productos = productoService.findAll(); // obtiene todos los productos
        var categorias = categoriaService.listarCategorias(); // obtiene categorías

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);

        return "client/listadeproductos"; // archivo HTML en templates/client/
    }
}
