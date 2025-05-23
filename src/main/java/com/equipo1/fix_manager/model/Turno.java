package com.equipo1.fix_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter@Setter
@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDate fecha;

    private LocalTime hora;

    @ManyToOne
    private Vehiculo vehiculo;


    @ManyToOne
    private Taller taller;

    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;


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
