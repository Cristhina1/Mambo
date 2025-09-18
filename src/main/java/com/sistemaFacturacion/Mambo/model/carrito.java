package com.sistemaFacturacion.Mambo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data
public class carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<detalleCarrito> detalles = new ArrayList<>();

    private LocalDate fechaCreacion;

    public Double getTotal() {
        return detalles.stream()
                .mapToDouble(detalleCarrito::getSubtotal)
                .sum();
    }
}
