package com.sistemaFacturacion.Mambo.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sistemaFacturacion.Mambo.Repository.ClienteRepository;
import com.sistemaFacturacion.Mambo.Repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UsuarioRepository userRepository;
    private final ClienteRepository clienteRepository;

    // AuthenticationManager para poder inyectarlo donde se necesite
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Configuración del AuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // PasswordEncoder con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // UserDetailsService para buscar usuarios y clientes
    @Bean
    public UserDetailsService userDetailService() {
        return username -> {
            return userRepository.findByNumeroDocumento(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                    .username(user.getNumeroDocumento())
                    .password(user.getPassword()) // la contraseña debe estar en BCrypt
                    .roles("ADMIN") // rol fijo para usuarios
                    .build()
                ).orElseGet(() ->
                    clienteRepository.findByNumeroDocumento(username)
                        .map(cliente -> org.springframework.security.core.userdetails.User.builder()
                            .username(cliente.getNumeroDocumento())
                            .password(cliente.getPassword()) // la contraseña debe estar en BCrypt
                            .roles("CLIENTE") // rol fijo para clientes
                            .build()
                        ).orElseThrow(() -> new UsernameNotFoundException("Usuario o Cliente no encontrado"))
                );
        };
    }
}
