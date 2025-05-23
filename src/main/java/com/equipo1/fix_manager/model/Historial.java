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
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

