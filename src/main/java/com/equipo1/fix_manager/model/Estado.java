package com.equipo1.fix_manager.model;

public enum Estado {
    PENDIENTE("Pendiente"),
    REALIZADO("Realizado"),
    CANCELADO("Cancelado");

    private final String descripcion;

    Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }


}