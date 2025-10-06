package com.sistemaFacturacion.Mambo.client.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.client.repository.ClienteRepository;
import com.sistemaFacturacion.Mambo.client.repository.TipoDocumentoRepository;
import com.sistemaFacturacion.Mambo.dto.ClienteDTO;
import com.sistemaFacturacion.Mambo.model.cliente;
import com.sistemaFacturacion.Mambo.model.tipoDocumento;

@Service
public class ClienteService {
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final TipoDocumentoRepository tDocumentoRepository;

    // Inyección de dependencias por constructor
    public ClienteService(ClienteRepository clienteRepository, TipoDocumentoRepository tDocumentoRepository) {
        this.clienteRepository = clienteRepository;
        this.tDocumentoRepository = tDocumentoRepository;
    }

    private cliente convertirAEntidad(ClienteDTO dto) {
        cliente c = new cliente();
        c.setId(dto.getId());
        c.setNombreCompleto(dto.getNombreCompleto());
        c.setNumeroDocumento(dto.getNumeroDocumento());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());

        tipoDocumento tDocumento = tDocumentoRepository.findById(dto.getTipoDocumento())
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
        c.setTipoDocumento(tDocumento);

        return c;
    }

    private ClienteDTO convertirADTO(cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNombreCompleto(c.getNombreCompleto());
        dto.setNumeroDocumento(c.getNumeroDocumento());
        dto.setEmail(c.getEmail());
        dto.setTelefono(c.getTelefono());
        if (c.getTipoDocumento() != null) {
        // Guardamos tanto el id como el nombre
        dto.setTipoDocumento(c.getTipoDocumento().getId());
    }
        return dto;
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .map(this:: convertirADTO);
    }

    public Optional<ClienteDTO> buscarPorNumeroDocumento(String numeroDocumento) {
        return clienteRepository.findByNumeroDocumento(numeroDocumento)
                .map(this::convertirADTO);
    }

    public Optional<ClienteDTO> obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertirADTO);
    }

    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        cliente c = new cliente();
        c.setNombreCompleto(clienteDTO.getNombreCompleto());
        c.setNumeroDocumento(clienteDTO.getNumeroDocumento());
        c.setEmail(clienteDTO.getEmail());
        c.setTelefono(clienteDTO.getTelefono());
        c.setContra(clienteDTO.getNumeroDocumento());

        // Buscar tipoDocumento por id
        if (clienteDTO.getTipoDocumento() != null) {
            tipoDocumento tDocumento = tDocumentoRepository.findById(clienteDTO.getTipoDocumento())
                    .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));
            c.setTipoDocumento(tDocumento);
        }

        cliente guardar = clienteRepository.save(c);
        return convertirADTO(guardar);
    }

    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        cliente c = clienteRepository.findById(id).orElse(null);
        if (c == null) {
            return null;
        }
        c.setNombreCompleto(clienteDTO.getNombreCompleto());
        c.setNumeroDocumento(clienteDTO.getNumeroDocumento());
        c.setEmail(clienteDTO.getEmail());
        c.setTelefono(clienteDTO.getTelefono());
        cliente actualizar = clienteRepository.save(c);
        return convertirADTO(actualizar);
    }

    public void eliminarCliente(long id) {
        clienteRepository.deleteById(id);
    }

}
