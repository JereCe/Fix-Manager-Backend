package com.equipo1.fix_manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TallerResponseDTO {

    private Long id;
    private String descripcion;
    private String ubicacion;
    private String imagenLogo;

    public TallerResponseDTO(Long id, String descripcion, String ubicacion, String imagenLogo) {
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.imagenLogo = imagenLogo;
    }


}
