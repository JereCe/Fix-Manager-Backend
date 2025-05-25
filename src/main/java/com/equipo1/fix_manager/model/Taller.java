package com.equipo1.fix_manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Taller {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String descripcion;
    private String ubicacion;
    private String imagenLogo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;


    public Taller() {
    }

    public Taller(Long id, String descripcion, String ubicacion, String imagenLogo, Agenda agenda) {
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.imagenLogo = imagenLogo;
        this.agenda = agenda;
    }
}
