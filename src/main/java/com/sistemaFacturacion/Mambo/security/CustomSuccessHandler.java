package com.sistemaFacturacion.Mambo.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
            for(GrantedAuthority auth : authentication.getAuthorities()) {
                if("ROLE_ADMIN".equals(auth.getAuthority())) {
                    response.sendRedirect("/admin/home");
                    return;
                } else if ("ROLE_VENDEDOR".equals(auth.getAuthority())) {
                    response.sendRedirect("/admin/boleta");
                    return;
                }
            }
            response.sendRedirect("/login?error");
        // Lógica personalizada después del inicio de sesión exitoso
    }
}
