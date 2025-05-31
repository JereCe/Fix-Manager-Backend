package com.equipo1.fix_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;

    private String descripcion;
    private String ubicacion;
    private String imagenLogo;

    @Column(name = "promedio_calificacion")
    private Double promedioCalificacion;

    @Column(name = "cantidad_calificaciones")
    private Long cantidadCalificaciones;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;


    public Taller() {
    }

    public Taller(Long id, String nombre, String descripcion, String ubicacion, String imagenLogo, Agenda agenda) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.imagenLogo = imagenLogo;
        this.agenda = agenda;
    }
}
