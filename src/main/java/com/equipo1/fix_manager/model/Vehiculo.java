package com.equipo1.fix_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter@Setter
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
