package com.sistemaFacturacion.Mambo.mape.mapeo;

import com.sistemaFacturacion.Mambo.entity.model.categoria;
import com.sistemaFacturacion.Mambo.mape.dto.CategoriaDTO;

public class CategoriaMape {
    public categoria toEntity(CategoriaDTO dto){
        categoria categoria = new categoria();
        categoria.setNombre(dto.getNombre());
        return categoria;
    }

    public CategoriaDTO toDto(categoria categoria){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setIcono(categoria.getImgCategoira());
        return dto;
    }
}
