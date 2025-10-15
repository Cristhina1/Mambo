package com.sistemaFacturacion.Mambo.controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class vistaProductoController {

    // 🛍️ Muestra la lista de productos del cliente
    @GetMapping("/listadeproductos")
    public String verListaProductos() {
        // Retorna el HTML de productos del cliente
        return "client/listadeproductos";
        // --> Archivo: src/main/resources/templates/client/listadeproductos.html
    }

    // 🛒 Muestra la vista del carrito del cliente
    @GetMapping("/carrito")
    public String verCarritoCliente() {
        // Retorna el HTML del carrito del cliente
        return "client/carrito";
        // --> Archivo: src/main/resources/templates/client/carrito.html
    }

    // 📜 Muestra el historial de compras del cliente
    @GetMapping("/historialcompra")
    public String verHistorialCompra(Model model) {
        // Aquí podrías pasar los datos del historial al modelo (en el futuro)
        return "client/historialCompra";
        // --> Archivo: src/main/resources/templates/client/historialCompra.html
    }

    // ✅ Acción del botón “Finalizar compra” (desde el carrito)
    @PostMapping("/finalizarcompra")
    public String finalizarCompra() {
        // Aquí podrías guardar la compra en la base de datos
        System.out.println("✅ Compra finalizada. Redirigiendo al historial de compras...");
        // Redirige al historial
        return "redirect:/client/historialcompra";
    }
}
