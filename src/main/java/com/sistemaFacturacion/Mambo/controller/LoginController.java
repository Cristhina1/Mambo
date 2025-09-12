package com.sistemaFacturacion.Mambo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

  @GetMapping({ "/", "/login" })
  public String mostrarLogin() {
    return "login"; // muestra login.html
  }

  @GetMapping("/logout")
  public String cerrarSesion(HttpSession session) {
    session.invalidate(); // cierra sesión
    return "redirect:/login";
  }
}
