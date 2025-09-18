package com.sistemaFacturacion.Mambo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String nombreCompleto;

    @Column(nullable = false, length = 100)
    private String tipoDocumento;

    @Column(unique = true, nullable = false, length = 20)
    private String numeroDocumento;

    @Email
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String contra;
    
}

