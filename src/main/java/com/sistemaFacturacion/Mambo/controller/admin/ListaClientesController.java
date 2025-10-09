package com.sistemaFacturacion.Mambo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistemaFacturacion.Mambo.Service.ClienteService;
import com.sistemaFacturacion.Mambo.Service.TipoDocumentoService;
import com.sistemaFacturacion.Mambo.dto.ClienteDTO;



@Controller
@RequestMapping("/lista/clientes")
public class ListaClientesController {
    private final ClienteService clienteService;
    private final TipoDocumentoService tDocumentoService;
    // Inyección por constructor
    public ListaClientesController(ClienteService clienteService, TipoDocumentoService tDocumentoService) {
        this.clienteService = clienteService;
        this.tDocumentoService = tDocumentoService;
    }

    @GetMapping
    public String listaVendedores(Model model) {
        var cliente = clienteService.listarClientes();

        //cantidad de clientes
        int cantClientes = cliente.size();

        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("cantClientes", cantClientes);
        model.addAttribute("cliente", new ClienteDTO());
        model.addAttribute("tiposDocumento", tDocumentoService.listarTodos());
        System.out.println("Tipos de Documento: " + tDocumentoService.listarTodos());
 
        return "admin/clientes";
    }

    @PostMapping("guardar")
    public String guardarCliente(@ModelAttribute ClienteDTO clienteDTO) {
        clienteService.crearCliente(clienteDTO);
        return "redirect:/lista/clientes";
    }

    @PostMapping("eliminar/{id}")
    public String postMethodName(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/lista/clientes";
    }
    
    
}
