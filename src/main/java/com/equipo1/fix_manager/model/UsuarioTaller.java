package com.equipo1.fix_manager.model;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UsuarioTaller {

    private Long id;
    private String email;
    private String contrasenia;

    private String nombre;

    private String apellido;

    private Taller taller;


    public UsuarioTaller() {
    }

    public UsuarioTaller(Long id, String email, String contrasenia, String nombre, String apellido, Taller taller) {
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.taller = taller;
    }
}
