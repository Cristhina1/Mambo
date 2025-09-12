package com.sistemaFacturacion.Mambo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {
    @Autowired
    private CustomSuccessHandler customSuccessHandler;
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("mambo123")
                .roles("ADMIN")
                .build();

        UserDetails vendedor = User.withDefaultPasswordEncoder()
                .username("vendedor")
                .password("vendedor123")
                .roles("VENDEDOR")
                .build();
        return new InMemoryUserDetailsManager(admin,vendedor);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/img/**", "/admin/principal").permitAll()
                        .requestMatchers("/admin/reporte").hasRole("ADMIN")
                        .requestMatchers("/admin/boleta","/admin/factura","/admin/productos","/admin/clientes", "/admin/home").hasAnyRole("VENDEDOR","ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(customSuccessHandler) // Puedes personalizar según el rol
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}
