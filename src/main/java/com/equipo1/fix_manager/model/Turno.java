package com.equipo1.fix_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private LocalDate fecha;

    private LocalTime hora;

    @ManyToOne
    private Vehiculo vehiculo;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @ManyToOne
    private UsuarioCliente cliente;

    @ManyToOne
    private Historial historial;

    public Turno() {
    }

    public Turno(Long id, LocalDate fecha, LocalTime hora, Vehiculo vehiculo, Estado estado, Agenda agenda, UsuarioCliente cliente, Historial historial) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.vehiculo = vehiculo;
        this.estado = estado;
        this.agenda = agenda;
        this.cliente = cliente;
        this.historial = historial;
    }
}
