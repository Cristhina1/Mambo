package com.sistemaFacturacion.Mambo.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "detalleCarrito")
public class detalleCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int cantidad;

    @ManyToOne
    private carrito carrito;
    
    @ManyToOne
    Producto producto;

    public Double getSubtotal() {
        return cantidad * producto.getPrecio();
    }
}
