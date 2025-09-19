package com.sistemaFacturacion.Mambo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

//usuarios que visitan la tienda
@Entity
@Data
public class cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCompleto;
    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private tipoDocumento tipoDocumento;

    @Column(unique = true, nullable = false, length = 20)
    private String numeroDocumento;
    @Email
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String contra;

    @OneToMany(mappedBy = "cliente")
    private List<carrito> carritos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Comprobante> comprobantes = new ArrayList<>();

}
