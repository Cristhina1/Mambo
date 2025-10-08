package com.sistemaFacturacion.Mambo.vendedor.service;

import com.sistemaFacturacion.Mambo.model.Vendedor;
import com.sistemaFacturacion.Mambo.vendedor.repository.VendedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    // Listar todos los vendedores
    public List<Vendedor> listarVendedores() {
        return vendedorRepository.findAll();
    }

    // Obtener vendedor por ID
    public Optional<Vendedor> obtenerPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    // Crear vendedor
    public Vendedor crearVendedor(Vendedor vendedor) {
        if (vendedorRepository.existsByDni(vendedor.getDni())) {
            throw new RuntimeException("El DNI ya está registrado");
        }
        return vendedorRepository.save(vendedor);
    }

    // Actualizar vendedor
    public Vendedor actualizarVendedor(Long id, Vendedor vendedorActualizado) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        vendedor.setNombre(vendedorActualizado.getNombre());
        vendedor.setTelefono(vendedorActualizado.getTelefono());
        vendedor.setCorreo(vendedorActualizado.getCorreo());
        vendedor.setDireccion(vendedorActualizado.getDireccion());
        vendedor.setActivo(vendedorActualizado.isActivo());

        return vendedorRepository.save(vendedor);
    }

    // Eliminar vendedor
    public void eliminarVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }
}
