package com.equipo1.fix_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter@Setter
@Entity
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Agenda agenda;

    private Float calificacion;

    private String descripcion;

    private String ubicacion;

    private List<Etiqueta> etiquetas;

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
