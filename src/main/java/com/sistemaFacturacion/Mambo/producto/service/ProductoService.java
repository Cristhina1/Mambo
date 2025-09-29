package com.sistemaFacturacion.Mambo.producto.service;

import com.sistemaFacturacion.Mambo.dto.ProductoDTO;
import com.sistemaFacturacion.Mambo.model.Producto;
import com.sistemaFacturacion.Mambo.model.ProductoImagen;
import com.sistemaFacturacion.Mambo.model.categoria;
import com.sistemaFacturacion.Mambo.producto.repository.CategoriaRepository;
import com.sistemaFacturacion.Mambo.producto.repository.ProductoRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    // 🔹 Inyección por constructor (más profesional)
    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Crear
    // producto-----------------------------------------------------------------------
    public Producto save(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaID())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        producto.setCategoria(categoria);
        if (productoDTO.getImagenes() != null && !productoDTO.getImagenes().isEmpty()) {
            List<ProductoImagen> imagenes = new ArrayList<>();
            for (MultipartFile file : productoDTO.getImagenes()) {
                try {
                    // Carpeta donde se guardarán las imágenes
                    String uploadDir = "uploads/";

                    // Si la carpeta no existe, crearla
                    File directorio = new File(uploadDir);
                    if (!directorio.exists()) {
                        directorio.mkdirs();
                    }
                    // Guardar archivo en la carpeta
                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    File destino = new File(uploadDir + fileName);
                    file.transferTo(destino);

                    // Crear entidad Imagen
                    ProductoImagen img = new ProductoImagen();
                    img.setUrl(uploadDir + fileName); // guardar solo la ruta relativa
                    img.setProducto(producto);
                    imagenes.add(img);

                    // Aquí puedes guardar en carpeta y solo persistir la URL
                } catch (IOException e) {
                    throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
                }
            }
            producto.setImagenes(imagenes);
        }
        return productoRepository.save(producto);
    }

    // Actualizar
    // producto-----------------------------------------------------------------------
    // 🔹 Actualizar producto existente
    public Producto actualizar(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        // Actualizar campos básicos
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());

        // Actualizar categoría
        categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaID())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        producto.setCategoria(categoria);

        // 🔹 Si se suben nuevas imágenes, reemplazar
        if (productoDTO.getImagenes() != null && !productoDTO.getImagenes().isEmpty()) {
            List<ProductoImagen> imagenes = new ArrayList<>();
            for (MultipartFile file : productoDTO.getImagenes()) {
                try {
                    String uploadDir = "uploads/";
                    File directorio = new File(uploadDir);
                    if (!directorio.exists()) {
                        directorio.mkdirs();
                    }

                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    File destino = new File(uploadDir + fileName);
                    file.transferTo(destino);

                    ProductoImagen img = new ProductoImagen();
                    img.setUrl(uploadDir + fileName);
                    img.setProducto(producto);
                    imagenes.add(img);

                } catch (IOException e) {
                    throw new RuntimeException("Error al guardar imagen: " + e.getMessage());
                }
            }
            producto.setImagenes(imagenes);
        }

        return productoRepository.save(producto);
    }

    // Listar todos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Buscar por ID
    public ProductoDTO findDtoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoriaID(producto.getCategoria().getId());

        // Si tiene imágenes
        if (producto.getImagenes() != null && !producto.getImagenes().isEmpty()) {
            List<String> urls = producto.getImagenes()
                    .stream()
                    .map(ProductoImagen::getUrl)
                    .toList();
            dto.setImagenesUrls(urls);
        }

        return dto;
    }

    // Eliminar por ID
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    // Buscar por nombre
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar por categoría
    public List<Producto> findByCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
}
