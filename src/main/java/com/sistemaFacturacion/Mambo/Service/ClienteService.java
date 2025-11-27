package com.sistemaFacturacion.Mambo.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sistemaFacturacion.Mambo.entity.Repository.ClienteRepository;
import com.sistemaFacturacion.Mambo.entity.Repository.RolRepository;
import com.sistemaFacturacion.Mambo.entity.model.TipoDocumento;
import com.sistemaFacturacion.Mambo.entity.model.cliente;
import com.sistemaFacturacion.Mambo.entity.model.rol;
import com.sistemaFacturacion.Mambo.mape.dto.ClienteDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.ClienteMape;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;
  private final RolRepository rolRepository;
  private final PasswordEncoder passwordEncoder;
  private final ClienteMape clienteMape;

  // ✅ Constructor injection (recomendado)
  public ClienteService(
      ClienteRepository clienteRepository,
      ClienteMape clienteMape,
      RolRepository rolRepository,
      PasswordEncoder passwordEncoder) {
    this.clienteRepository = clienteRepository;
    this.rolRepository = rolRepository;
    this.passwordEncoder = passwordEncoder;
    this.clienteMape = clienteMape;
  }

  // ✅ Listar todos los clientes
  public List<ClienteDTO> listarClientes() {
    return clienteRepository.findAll()
        .stream()
        .map(clienteMape::toDto)
        .collect(Collectors.toList());
  }

  // ✅ Buscar por email
  public ClienteDTO buscarPorEmail(String email) {
    cliente entity = clienteRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con email: " + email));
    return clienteMape.toDto(entity);

  }

  // ✅ Buscar por número de documento
  public ClienteDTO buscarPorNumeroDocumento(String numeroDocumento) {
      cliente entity = clienteRepository.findByNumeroDocumento(numeroDocumento)
          .orElseThrow(() -> new RuntimeException("Cliente no encontrado con número de documento: " + numeroDocumento));
      return clienteMape.toDto(entity);
  }

  // ✅ Buscar por ID
  public Optional<ClienteDTO> obtenerPorId(Long id) {
    return clienteRepository.findById(id)
        .map(clienteMape::toDto);
  }

  // ✅ Crear cliente con validaciones y contraseña encriptada
  public cliente crearCliente(ClienteDTO clienteDTO) {

    // 🔸 Validar duplicados por email o documento
    if (clienteRepository.findByNumeroDocumento(clienteDTO.getNumeroDocumento()).isPresent()) {
      throw new RuntimeException("Ya existe un cliente con ese número de documento");
    }

    if (clienteRepository.findByEmail(clienteDTO.getEmail()).isPresent()) {
      throw new RuntimeException("Ya existe un cliente con ese email");
    }

    cliente c = new cliente();
    c.setNombreCompleto(clienteDTO.getNombreCompleto());
    c.setNumeroDocumento(clienteDTO.getNumeroDocumento());
    c.setEmail(clienteDTO.getEmail());
    c.setTelefono(clienteDTO.getTelefono());

    // 🔐 Contraseña encriptada
    String contraseniaBase = clienteDTO.getContra();
    if (!StringUtils.hasText(contraseniaBase)) {
      // Si el DTO no trae contraseña, usa el número de documento como base
      contraseniaBase = clienteDTO.getNumeroDocumento();
    }
    c.setContra(passwordEncoder.encode(contraseniaBase));

    if (clienteDTO.getTipoDocumento() == null) {
      throw new RuntimeException("Debe seleccionar un tipo de documento");
    }

    String tipo = clienteDTO.getTipoDocumento().toString();

    TipoDocumento tDocumento;
    try {
      tDocumento = TipoDocumento.valueOf(tipo);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Tipo de documento no válido: " + tipo);
    }

    c.setTipoDocumento(tDocumento);

    rol rolCliente = rolRepository.findByNombre("CLIENTE")
        .orElseThrow(() -> new RuntimeException("Rol CLIENTE no encontrado"));
    c.setRol(rolCliente);

    c.setEnabled(true);

    return clienteRepository.save(c);
  }

  // ✅ Actualizar cliente existente
  public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
    cliente c = clienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    c.setNombreCompleto(clienteDTO.getNombreCompleto());
    c.setNumeroDocumento(clienteDTO.getNumeroDocumento());
    c.setEmail(clienteDTO.getEmail());
    c.setTelefono(clienteDTO.getTelefono());

    // Si el DTO trae una nueva contraseña, actualizarla
    if (StringUtils.hasText(clienteDTO.getContra())) {
      c.setContra(passwordEncoder.encode(clienteDTO.getContra()));
    }

    cliente actualizado = clienteRepository.save(c);
    return clienteMape.toDto(actualizado);
  }

  // ✅ Eliminar cliente
  public void eliminarCliente(long id) {
    if (!clienteRepository.existsById(id)) {
      throw new RuntimeException("El cliente con ID " + id + " no existe");
    }
    clienteRepository.deleteById(id);
  }

  // ✅ Filtro de clientes
  public List<ClienteDTO> filtrarClientes(String buscar, Long tipoDocumentoId, String estado) {
    List<cliente> todosLosClientes = clienteRepository.findAll();

    return todosLosClientes.stream()
        .filter(c -> {
          if (!StringUtils.hasText(buscar))
            return true;
          String busquedaLower = buscar.toLowerCase();

          boolean coincideNombre = c.getNombreCompleto() != null &&
              c.getNombreCompleto().toLowerCase().contains(busquedaLower);
          boolean coincideDocumento = c.getNumeroDocumento() != null &&
              c.getNumeroDocumento().toLowerCase().contains(busquedaLower);

          return coincideNombre || coincideDocumento;
        })
        .filter(c -> {
          if (tipoDocumentoId == null || tipoDocumentoId == 0)
            return true;
          return c.getTipoDocumento() != null;
        })
        .map(clienteMape::toDto)
        .collect(Collectors.toList());
  }

  // Devuelve la entidad directamente
  public cliente obtenerEntidadPorId(Long id) {
    return clienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + id));
  }

}
