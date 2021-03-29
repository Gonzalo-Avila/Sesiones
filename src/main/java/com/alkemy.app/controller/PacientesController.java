package com.alkemy.app.controller;

import com.alkemy.app.filters.EstadoTratamientoParaFiltro;
import com.alkemy.app.filters.FiltroPacientes;
import com.alkemy.app.model.EstadoTratamiento;
import com.alkemy.app.model.Paciente;
import com.alkemy.app.repositories.PacientesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PacientesController {

    @Autowired
    public PacientesDAO pacientesDAO;

    @GetMapping("pacientes")
    public String mostrarPacientes(FiltroPacientes filtro, Model model) {

        System.out.println("Alguien pidió los pacientes");

        List<Paciente> pacientes = pacientesDAO.findAll().stream()
                .filter(p -> (filtro.getEstadoTratamiento() == null ||
                                filtro.getEstadoTratamiento() == EstadoTratamientoParaFiltro.CUALQUIERA ||
                        p.getEstadoTratamiento().toString().equals(filtro.getEstadoTratamiento().toString())) &&
                        (filtro.getNombre() == null ||
                                p.getNombre().concat(" " + p.getApellido()).toLowerCase().contains(filtro.getNombre().toLowerCase()))
                        && (filtro.getDni() == null || p.getDni().contains(filtro.getDni()))).collect(Collectors.toList());

        model.addAttribute("filtro", filtro);
        model.addAttribute("estados", EstadoTratamientoParaFiltro.values());
        model.addAttribute("pacientes",pacientes);
        return "pacientes";
    }

    @GetMapping("pacientes/{id}")
    public String mostrarPaciente(Paciente paciente, Model model) {
        System.out.println("Alguien pidió un paciente");

        Optional<Paciente> pacienteOptional = pacientesDAO.findById(paciente.getId());

        if(pacienteOptional.isPresent()){
            paciente = pacienteOptional.get();
            model.addAttribute("paciente",paciente);
            return "paciente";
        }
        else{
            return "not_found_error";
        }
    }

    @GetMapping("pacientes/{id}/edicion")
    public String formEdicionPaciente(Paciente paciente, Model model) {
        System.out.println("Alguien pidió el form para editar un paciente");

        Optional<Paciente> pacienteOptional = pacientesDAO.findById(paciente.getId());

        if(pacienteOptional.isPresent()){
            paciente = pacienteOptional.get();
            model.addAttribute("paciente",paciente);
            model.addAttribute("estados", EstadoTratamiento.values());
            return "form_edicion_paciente";
        }
        else{
            return "not_found_error";
        }
    }
    @PostMapping("pacientes/{id}")
    public String editarPaciente(@Valid Paciente paciente, Errors errores, Model model) {
        System.out.println("Alguien está guardando la edición de un paciente");

        if(errores.hasErrors()){
            model.addAttribute("estados", EstadoTratamiento.values());
            return "form_edicion_paciente";
        }

        pacientesDAO.save(paciente);
        return "redirect:/pacientes/{id}";
    }

    @GetMapping("pacientes/nuevo")
    public String formNuevoPaciente(Paciente paciente, Model model) {
        System.out.println("Alguien pidió el form para crear nuevo paciente");
        model.addAttribute("estados", EstadoTratamiento.values());

        return "form_nuevo_paciente";
    }

    @PostMapping("pacientes")
    public String crearPaciente(@Valid Paciente paciente, Errors errores, Model model) {
        System.out.println("Alguien está guardando un nuevo paciente");

        if(errores.hasErrors()){
            model.addAttribute("estados", EstadoTratamiento.values());
            return "form_nuevo_paciente";
        }

        pacientesDAO.save(paciente);
        return "home";
    }

}

