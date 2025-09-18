package com.sistemaFacturacion.Mambo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreCompleto;
    private String tipoDocumento;
    private String numeroDocumento;
    private String email;
    private String telefono;
    private String contra;
}

