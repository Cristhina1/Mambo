package com.sistemaFacturacion.Mambo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String contra;
    
    private String estado; // Ejemplo: "Activo" o "Inactivo"
}

