package com.equipo1.fix_manager.model;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Etiqueta {
    private Long id;

    private String nombre;


    public Etiqueta() {
    }

    public Etiqueta(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
