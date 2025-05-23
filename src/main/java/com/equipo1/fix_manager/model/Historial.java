package com.equipo1.fix_manager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class Historial {

    private Long id;
    private String trabajoRealizado;
    private Turno turno;

    private List<String> fotos;

    private String descripcion;

    public Historial() {
    }


    public Historial(Long id, String trabajoRealizado, Turno turno, List<String> fotos, String descripcion) {
        this.id = id;
        this.trabajoRealizado = trabajoRealizado;
        this.turno = turno;
        this.fotos = fotos;
        this.descripcion = descripcion;
    }


}

