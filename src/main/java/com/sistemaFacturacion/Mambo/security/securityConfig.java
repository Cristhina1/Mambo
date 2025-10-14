package com.sistemaFacturacion.Mambo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 🔒 Deshabilitamos CSRF para desarrollo (puedes habilitarlo después si usas formularios seguros)
                .csrf(csrf -> csrf.disable())
                // 🔑 Configuramos permisos para las rutas
                .authorizeHttpRequests(auth -> auth
                        // ✅ Rutas públicas (no necesitan login)
                        .requestMatchers(
                                "/login",
                                "/css/**",
                                "/js/**",
                                "/img/**",
                                "/admin/principal",
                                "/cliente/historial",
                                "/client/fragments/historial-compras"
                        ).permitAll()

                        // 👑 Solo el ADMIN puede ver reportes
                        .requestMatchers("/admin/reporte").hasRole("ADMIN")

                        // 👥 ADMIN o VENDEDOR pueden acceder a estas rutas
                        .requestMatchers(
                                "/admin/boleta",
                                "/admin/factura",
                                "/admin/productos/**",
                                "/lista/clientes/**",
                                "/admin/home",
                                "/lista/vendedores/**",
                                "/cliente/productos"
                        ).hasAnyRole("ADMIN", "VENDEDOR")

                        // 🧍 CLIENTE logueado puede acceder a sus secciones privadas
                        .requestMatchers(
                                "/cliente/**",
                                "/carrito/**",
                                "/productos/**"
                        ).hasRole("CLIENTE")

                        // 🚫 Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )

                // 🔐 Configuración del formulario de login
                .formLogin(form -> form
                        .loginPage("/login")  // Ruta del login
                        .usernameParameter("username") // DNI o usuario
                        .passwordParameter("password") // Contraseña
                        .successHandler(customSuccessHandler) // Redirección según rol
                        .permitAll()
                )

                // 🚪 Configuración del logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    // 🔧 Configuración del AuthenticationManager
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    // 🔐 Encriptador de contraseñas con BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
