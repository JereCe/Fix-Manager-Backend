package com.equipo1.fix_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String trabajoRealizado;

    @OneToMany(mappedBy = "historial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Turno> turnos = new ArrayList<>();

    @ElementCollection
    private List<String> fotos = new ArrayList<>();

    private String descripcion;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    public Historial() {
    }

    public Historial(Long id, String trabajoRealizado, List<Turno> turnos, List<String> fotos, String descripcion, Vehiculo vehiculo) {
        this.id = id;
        this.trabajoRealizado = trabajoRealizado;
        this.turnos = turnos;
        this.fotos = fotos;
        this.descripcion = descripcion;
        this.vehiculo = vehiculo;
    }


}
