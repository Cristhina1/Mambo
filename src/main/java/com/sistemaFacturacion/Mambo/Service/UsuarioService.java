package com.sistemaFacturacion.Mambo.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.entity.Repository.RolRepository;
import com.sistemaFacturacion.Mambo.entity.Repository.UsuarioRepository;
import com.sistemaFacturacion.Mambo.entity.model.TipoDocumento;
import com.sistemaFacturacion.Mambo.entity.model.Usuario;
import com.sistemaFacturacion.Mambo.entity.model.rol;
import com.sistemaFacturacion.Mambo.mape.dto.VendedorDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.UsuarioMape;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMape usuarioMape;

    public Usuario register(VendedorDTO dto) {
        rol rolUsuario = rolRepository.findByNombre(dto.getRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Usuario usuario = Usuario.builder()
                .nombreCompleto(dto.getNombreCompleto())
                .tipoDocumento(TipoDocumento.valueOf(dto.getTipoDocumento()))
                .rol(rolUsuario)
                .numeroDocumento(dto.getNumeroDocumento())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .contra(passwordEncoder.encode(dto.getContra()))
                .enabled(true)
                .build();

        return usuarioRepository.save(usuario);
    }

    public List<VendedorDTO> listar(){
        return usuarioRepository.findAll()
            .stream()
            .map(usuarioMape::toDto)
            .toList();
    }
}
