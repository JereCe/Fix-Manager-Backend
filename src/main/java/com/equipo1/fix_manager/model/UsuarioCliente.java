package com.equipo1.fix_manager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
public class UsuarioCliente {

    private Long id;
    private String email;
    private String contrasenia;

    private String nombre;

    private String apellido;

     private List<Vehiculo> vehiculos;

     private List<Turno> turnos;

    public UsuarioCliente() {
    }

    public UsuarioCliente(Long id, String email, String contrasenia, String nombre, String apellido, List<Vehiculo> vehiculos, List<Turno> turnos) {
        this.id = id;
        this.email = email;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.vehiculos = vehiculos;
        this.turnos = turnos;
    }


}
