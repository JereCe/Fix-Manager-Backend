package com.equipo1.fix_manager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter@Setter
public class HistorialTurnoDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private String taller;
    private String descripcionTrabajo;
    private List<String> imagenes;

    public HistorialTurnoDTO(Long id, LocalDate fecha, LocalTime hora, String taller, String descripcionTrabajo, List<String> imagenes) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.taller = taller;
        this.descripcionTrabajo = descripcionTrabajo;
        this.imagenes = imagenes;
    }

    // Getters y setters
}
