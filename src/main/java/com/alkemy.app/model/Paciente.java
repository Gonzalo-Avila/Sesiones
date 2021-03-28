package com.alkemy.app.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;


@Entity
@Table(name = "pacientes")
public class Paciente {

    @Column(name = "paciente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Column(name = "paciente_nombre")
    String nombre;

    @NotEmpty(message = "El apellido no puede estar vacío")
    @NotEmpty
    @Column(name = "paciente_apellido")
    String apellido;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "paciente_fecha_nacimiento")
    LocalDate fechaNacimiento;

    @Column(name = "paciente_dni")
    String dni;

    @Column(name = "paciente_telefono")
    String telefono;

    @Column(name = "paciente_detalles")
    String detalles;

    @Email
    @Column(name = "paciente_email")
    String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "paciente_estado_tratamiento")
    EstadoTratamiento estadoTratamiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEmail() {
        return email;
    }

    public EstadoTratamiento getEstadoTratamiento() {
        return estadoTratamiento;
    }

    public void setEstadoTratamiento(EstadoTratamiento estadoTratamiento) {
        this.estadoTratamiento = estadoTratamiento;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
