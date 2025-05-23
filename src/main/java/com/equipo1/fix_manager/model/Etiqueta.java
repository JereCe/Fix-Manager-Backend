package com.equipo1.fix_manager.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;


    public Etiqueta() {
    }

    public Etiqueta(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
