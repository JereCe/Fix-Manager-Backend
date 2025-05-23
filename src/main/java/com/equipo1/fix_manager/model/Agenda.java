package com.equipo1.fix_manager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter@Setter
public class Agenda {

    private Long id;

    private List<Turno> turnos;

    public Agenda() {
    }

    public Agenda(Long id, List<Turno> turnos) {
        this.id = id;
        this.turnos = turnos;
    }
}
