package com.sistemaFacturacion.Mambo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(reporteController.class)
@AutoConfigureMockMvc
class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}) // 👈 Simulamos un admin
    void testMostrarReporte() throws Exception {
        mockMvc.perform(get("/admin/reporte"))
                .andExpect(status().isOk()) // Esperamos 200 OK
                .andExpect(view().name("admin/reporte")) // Esperamos la vista
                .andExpect(model().attribute("seccionActiva", "reporte")); // Esperamos el atributo
    }
}
