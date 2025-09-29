package com.sistemaFacturacion.Mambo.security;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.client.repository.ClienteRepository;
import com.sistemaFacturacion.Mambo.model.Usuario;
import com.sistemaFacturacion.Mambo.model.cliente;
import com.sistemaFacturacion.Mambo.usuarios.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository,
                                    ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String numeroDocumento) throws UsernameNotFoundException {

        // Primero buscar en usuarios
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNumeroDocumento(numeroDocumento);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            GrantedAuthority authority = new SimpleGrantedAuthority(
                    "ROLE_" + usuario.getRol().getNombre().toUpperCase()
            );
            return new org.springframework.security.core.userdetails.User(
                    usuario.getNumeroDocumento(),
                    usuario.getContra(),
                    Collections.singletonList(authority)
            );
        }

        // Luego buscar en clientes
        Optional<cliente> clienteOpt = clienteRepository.findByNumeroDocumento(numeroDocumento);
        if (clienteOpt.isPresent()) {
            cliente cliente = clienteOpt.get();
            GrantedAuthority authority = new SimpleGrantedAuthority(
                    "ROLE_" + cliente.getRol().getNombre().toUpperCase()
            );
            return new org.springframework.security.core.userdetails.User(
                    cliente.getNumeroDocumento(),
                    cliente.getContra(),
                    Collections.singletonList(authority)
            );
        }

        throw new UsernameNotFoundException(
                "Usuario no encontrado con número de documento: " + numeroDocumento
        );
    }
}
