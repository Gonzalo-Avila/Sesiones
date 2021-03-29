package com.alkemy.app.controller;

import com.alkemy.app.repositories.UsuariosDAO;
import com.alkemy.app.security.EncripcionPassword;
import com.alkemy.app.security.Rol;
import com.alkemy.app.security.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class UsuariosController {

    @Autowired
    UsuariosDAO usuariosDAO;

    @GetMapping("usuarios/nuevo")
    public String formNuevoUsuario(Usuario usuario, Model model) {
        System.out.println("Alguien pidió el form para crear un nuevo usuario");

        model.addAttribute("roles", Rol.values());

        return "form_nuevo_usuario";
    }

    @PostMapping("usuarios")
    public String crearUsuario(Usuario usuario) {
        System.out.println("Alguien está creando un nuevo usuario");

        usuario.setPassword(EncripcionPassword.encriptar(usuario.getPassword()));
        usuariosDAO.save(usuario);
        return "redirect:/";
    }

}
