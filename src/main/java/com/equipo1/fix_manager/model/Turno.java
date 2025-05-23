package com.equipo1.fix_manager.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter@Setter
public class Turno {

    private Long id;

    private LocalDate fecha;

    private LocalTime hora;

    private Vehiculo vehiculo;

    private Taller taller;

    private Estado estado;


    public Turno() {
    }

    public Turno(Long id, LocalDate fecha, LocalTime hora, Vehiculo vehiculo, Taller taller, Estado estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.vehiculo = vehiculo;
        this.taller = taller;
        this.estado = estado;
    }


}
