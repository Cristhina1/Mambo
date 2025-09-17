package com.sistemaFacturacion.Mambo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long idProducto;
    Integer cantidad;
    Double precioUnitario;
    Double subtotal;
    Long idCliente;
}
