package com.equipo1.fix_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter@Setter
public class TallerDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String imagenLogo;
    private Double promedioCalificacion;
}