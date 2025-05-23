package com.equipo1.fix_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
@Getter@Setter
@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "agenda",cascade = CascadeType.ALL)
    private List<Turno> turnos;

    public Agenda() {
    }

    public Agenda(Long id, List<Turno> turnos) {
        this.id = id;
        this.turnos = turnos;
    }
}
