package com.equipo1.fix_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String modelo;

    private String marca;

    private String patente;

    private Integer anio;

    @ManyToOne
    private UsuarioCliente propietario;

    @OneToOne(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private Historial historial;

    public Vehiculo() {
    }

    public Vehiculo(Long id, String modelo, String marca, String patente, Integer anio, UsuarioCliente propietario, Historial historial) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.patente = patente;
        this.anio = anio;
        this.propietario = propietario;
        this.historial = historial;
    }
}
