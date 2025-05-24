package com.equipo1.fix_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    private Float calificacion;

    private String descripcion;

    private String ubicacion;

    @ManyToMany
    @JoinTable(
            name = "taller_etiqueta",
            joinColumns = @JoinColumn(name = "taller_id"),
            inverseJoinColumns = @JoinColumn(name = "etiqueta_id")
    )
    private List<Etiqueta> etiquetas = new ArrayList<>();

    public Taller() {
    }

    public Taller(Long id, Agenda agenda, Float calificacion, String descripcion, String ubicacion, List<Etiqueta> etiquetas) {
        this.id = id;
        this.agenda = agenda;
        this.calificacion = calificacion;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.etiquetas = etiquetas;


    }


}
