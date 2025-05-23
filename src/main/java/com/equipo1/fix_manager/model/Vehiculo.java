package com.equipo1.fix_manager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter@Setter
public class Vehiculo {

    private Long id;

    private String modelo;

    private String marca;
    private String patente;

    private Date anio;


    private List<Historial> historial;


    public Vehiculo() {
    }

    public Vehiculo(Long id, String modelo, String marca, String patente, Date anio, List<Historial> historial) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.patente = patente;
        this.anio = anio;
        this.historial = historial;
    }
}
