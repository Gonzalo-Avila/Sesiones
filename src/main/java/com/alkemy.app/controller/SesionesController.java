package com.alkemy.app.controller;

import com.alkemy.app.filters.FiltroSesiones;
import com.alkemy.app.model.EstadoTratamiento;
import com.alkemy.app.model.Paciente;
import com.alkemy.app.model.Sesion;
import com.alkemy.app.repositories.PacientesDAO;
import com.alkemy.app.repositories.SesionesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class SesionesController {

    @Autowired
    public SesionesDAO sesionesDAO;

    @Autowired
    public PacientesDAO pacientesDAO;

    @GetMapping("sesiones")
    public String mostrarSesiones(FiltroSesiones filtro, Model model) {

        System.out.println("Alguien pidió las sesiones");

        List<Sesion> sesiones = sesionesDAO.findAll().stream()
                .filter(s -> (filtro.getFechaDesde() == null || filtro.getFechaDesde().isBefore(s.getFecha())) &&
                        (filtro.getFechaHasta() == null || filtro.getFechaHasta().isAfter(s.getFecha())) &&
                        (filtro.getNombrePaciente() == null ||
                                s.getPaciente().getNombre().concat(" " + s.getPaciente().getApellido()).toLowerCase()
                        .contains(filtro.getNombrePaciente().toLowerCase()))).collect(Collectors.toList());

        model.addAttribute("filtro", filtro);
        model.addAttribute("sesiones",sesiones);
        return "sesiones";
    }

    @GetMapping("sesiones/{id}")
    public String mostrarSesion(Sesion sesion, Model model) {
        System.out.println("Alguien pidió una sesión");

        Optional<Sesion> sesionOptional = sesionesDAO.findById(sesion.getId());

        if(sesionOptional.isPresent()){
            sesion = sesionOptional.get();
            model.addAttribute("sesion",sesion);
            return "sesion";
        }
        else{
            return "not_found_error";
        }
    }

    @GetMapping("sesiones/{id}/edicion")
    public String formEdicionSesion(Sesion sesion, Model model) {
        System.out.println("Alguien pidió el form para editar una sesión");

        Optional<Sesion> sesionOptional = sesionesDAO.findById(sesion.getId());

        if(sesionOptional.isPresent()){
            sesion = sesionOptional.get();
            model.addAttribute("sesion",sesion);
            model.addAttribute("pacientes", pacientesDAO.findAll());
            return "form_edicion_sesion";
        }
        else{
            return "not_found_error";
        }
    }

    @GetMapping("sesiones/nueva")
    public String formNuevaSesion(Sesion sesion, Model model) {
        System.out.println("Alguien pidió el form para crear nueva sesión");

        model.addAttribute("pacientes", pacientesDAO.findAll());
        return "form_nueva_sesion";
    }

    @PostMapping("sesiones/{id}")
    public String editarSesion(Sesion sesion) {
        System.out.println("Alguien está guardando la edición de una sesión");

        sesionesDAO.save(sesion);
        return "redirect:/sesiones/{id}";
    }

    @PostMapping("sesiones")
    public String crearSesion(Sesion sesion) {
        System.out.println("Alguien está guardando una nueva sesión");

        sesionesDAO.save(sesion);
        return "redirect:/sesiones";
    }
}
