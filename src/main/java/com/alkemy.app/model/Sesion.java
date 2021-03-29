package com.alkemy.app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sesiones")
public class Sesion {

    @Column(name = "sesion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @NotNull(message = "La sesión requiere un paciente")
    @ManyToOne
    @JoinColumn(name = "sesion_paciente")
    Paciente paciente;

    @NotNull(message = "Por favor indicá una fecha")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "sesion_fecha")
    LocalDate fecha;

    @Column(name = "sesion_notas")
    String notas;

    @Column(name = "sesion_precio_facturado")
    BigDecimal precioFacturado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public BigDecimal getPrecioFacturado() {
        return precioFacturado;
    }

    public void setPrecioFacturado(BigDecimal precioFacturado) {
        this.precioFacturado = precioFacturado;
    }
}
