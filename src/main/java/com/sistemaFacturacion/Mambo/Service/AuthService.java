package com.sistemaFacturacion.Mambo.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.Repository.RolRepository;
import com.sistemaFacturacion.Mambo.Repository.UsuarioRepository;
import com.sistemaFacturacion.Mambo.dto.AuthResponse;
import com.sistemaFacturacion.Mambo.dto.LoginRequest;
import com.sistemaFacturacion.Mambo.dto.RegisterRequest;
import com.sistemaFacturacion.Mambo.model.Usuario;
import com.sistemaFacturacion.Mambo.model.rol; // 👈 asegúrate de usar Rol con mayúscula
import com.sistemaFacturacion.Mambo.security.JwtService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository userRepository;
    private final RolRepository roleRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getNumeroDocumento(),
                        request.getPassword()));

        Usuario user = userRepository.findByNumeroDocumento(request.getNumeroDocumento())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtService.getToken(user); // acepta User
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
       rol userRole = roleRepository.findByNombre("ROLE_USER")
        .orElseThrow(() -> new RuntimeException("Rol USER no encontrado en la base de datos"));

        Usuario user = Usuario.builder()
                .numeroDocumento(request.getNumeroDocumento())
                .contra(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .rol(userRole)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()

                .token(jwtService.getToken(user))
                .build();

    }
}
