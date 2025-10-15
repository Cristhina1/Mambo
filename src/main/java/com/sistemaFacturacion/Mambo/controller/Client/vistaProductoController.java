package com.sistemaFacturacion.Mambo.controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class vistaProductoController {
    // Muestra la lista de productos del cliente
    @GetMapping("/listadeproductos")
    public String verListaProductos() {
        // Este método retorna el nombre del archivo HTML de productos del cliente
        return "client/listadeproductos"; 
        // --> ubica tu archivo en: src/main/resources/templates/cliente/listaProductos.html
    }

    // ✅ Muestra la vista del carrito del cliente
    @GetMapping("/cliente/carrito")
    public String verCarritoCliente() {
        // Este método retorna el nombre del archivo HTML del carrito del cliente
        return "cliente/carrito";
        // --> ubica tu archivo en: src/main/resources/templates/cliente/carrito.html
    }
}
