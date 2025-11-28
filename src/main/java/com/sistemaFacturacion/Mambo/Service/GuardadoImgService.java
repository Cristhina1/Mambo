package com.sistemaFacturacion.Mambo.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GuardadoImgService {
    private final String RUTA_IMAGENES = "uploads/productos/";

    public String guardarImagen(MultipartFile img){
        try{
            Files.createDirectories(Paths.get(RUTA_IMAGENES));
            String fileName = UUID.randomUUID() + "_" + img.getOriginalFilename();
            Path path = Paths.get(RUTA_IMAGENES + fileName);

            Files.write(path, img.getBytes());

            // URL pública para Angular
            return "/uploads/productos/" + fileName;
        } catch (Exception e){
            throw new RuntimeException("Error al guardar la imagen", e);
        }
    }
}
