package com.sistemaFacturacion.Mambo.controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class vistaClientController {
    @GetMapping
    public String verInicioCliente() {
        // Este método retorna el nombre del archivo HTML de inicio del cliente
        return "client/inicio"; 
        // --> ubica tu archivo en: src/main/resources/templates/client/inicio.html
    }
}
