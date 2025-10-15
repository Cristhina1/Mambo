package com.sistemaFacturacion.Mambo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.sistemaFacturacion.Mambo.Service.ClienteService;
import com.sistemaFacturacion.Mambo.Service.TipoDocumentoService;
import com.sistemaFacturacion.Mambo.dto.ClienteDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/lista/clientes")
public class ListaClientesController {

    private final ClienteService clienteService;
    private final TipoDocumentoService tDocumentoService;

    public ListaClientesController(ClienteService clienteService, TipoDocumentoService tDocumentoService) {
        this.clienteService = clienteService;
        this.tDocumentoService = tDocumentoService;
    }

    @GetMapping
    public String listaVendedores(Model model) {
        var clientes = clienteService.listarClientes(); // Llamar solo una vez

        model.addAttribute("clientes", clientes);
        model.addAttribute("cantClientes", clientes.size());
        model.addAttribute("cliente", new ClienteDTO()); // Objeto vacío para el formulario de creación
        model.addAttribute("tiposDocumento", tDocumentoService.listarTodos());

        return "admin/clientes";
    }

    @GetMapping("/obtener/{id}")
    @ResponseBody // Le dice a Spring que devuelva directamente el objeto como JSON
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        // Usa el método del service para buscar el cliente
        return clienteService.obtenerPorId(id)
                .map(ResponseEntity::ok) // Si lo encuentra, devuelve 200 OK con el cliente en JSON
                .orElse(ResponseEntity.notFound().build()); // Si no lo encuentra, devuelve 404 Not Found
    }

    @PostMapping("guardar")
    public String guardarCliente(@ModelAttribute ClienteDTO clienteDTO) {
        clienteService.crearCliente(clienteDTO);
        return "redirect:/lista/clientes";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Long id, @ModelAttribute ClienteDTO clienteDTO) {
        // Establecer el ID en el DTO para asegurar que el servicio sepa a quién
        // actualizar
        clienteDTO.setId(id);
        clienteService.actualizarCliente(id, clienteDTO);
        return "redirect:/lista/clientes";
    }

    @PostMapping("eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/lista/clientes";
    }

    @GetMapping("/filtrar")
    @ResponseBody // Indica que la respuesta debe ser el cuerpo de datos (JSON)
    public ResponseEntity<List<ClienteDTO>> filtrarClientes(
            @RequestParam(required = false) String buscar,
            // El JS envía el ID del tipo de documento como 'tipo'
            @RequestParam(required = false, name = "tipo") Long tipoDocumentoId,
            @RequestParam(required = false) String estado) {
        // Llama al método de filtrado que acabas de implementar en ClienteService
        List<ClienteDTO> clientesFiltrados = clienteService.filtrarClientes(buscar, tipoDocumentoId, estado);

        // Devuelve la lista en formato JSON al JavaScript
        return ResponseEntity.ok(clientesFiltrados);
    }
}