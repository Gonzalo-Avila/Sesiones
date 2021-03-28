package com.alkemy.app.filters;

import com.alkemy.app.model.EstadoTratamiento;

public class FiltroPacientes {
    String nombre;
    String dni;
    EstadoTratamientoParaFiltro estadoTratamiento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public EstadoTratamientoParaFiltro getEstadoTratamiento() {
        return estadoTratamiento;
    }

    public void setEstadoTratamiento(EstadoTratamientoParaFiltro estadoTratamiento) {
        this.estadoTratamiento = estadoTratamiento;
    }
}
