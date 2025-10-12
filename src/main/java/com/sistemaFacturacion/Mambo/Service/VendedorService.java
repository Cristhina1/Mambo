package com.sistemaFacturacion.Mambo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.Repository.VendedorRepository;
import com.sistemaFacturacion.Mambo.model.Vendedor;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }
    public List<Vendedor> listar() { return vendedorRepository.findAll(); }

    public Vendedor guardar(Vendedor v) { return vendedorRepository.save(v); }

    public Vendedor buscar(Long id) { return vendedorRepository.findById(id).orElse(null); }

    public void eliminar(Long id) { vendedorRepository.deleteById(id); }
}
