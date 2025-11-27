package com.sistemaFacturacion.Mambo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.entity.Repository.UsuarioRepository;
import com.sistemaFacturacion.Mambo.entity.model.Usuario;
import com.sistemaFacturacion.Mambo.mape.dto.VendedorDTO;

@Service
public class VendedorService {

    private final UsuarioRepository usuarioRepository;

    public VendedorService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public List<Usuario> obtenerVendedores() {
        return usuarioRepository.listarVendedores();
    }

    public List<VendedorDTO> listar() {
        return usuarioRepository.findByRolNombre("VENDEDOR").stream()
                .map(this::convertirADTO)
                .toList();
    }

    public VendedorDTO buscarDTO(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    private VendedorDTO convertirADTO(Usuario u) {
        VendedorDTO dto = new VendedorDTO();
        dto.setId(u.getId());
        dto.setNombreCompleto(u.getNombreCompleto());
        dto.setNumeroDocumento(u.getNumeroDocumento());
        dto.setEmail(u.getEmail());
        dto.setTelefono(u.getTelefono());
        return dto;
    }
}