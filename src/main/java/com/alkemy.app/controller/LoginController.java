package com.alkemy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("asd")
    public String loginForm(Model model) {
        System.out.println("Alguien pidio el login");
        //ModelAndView mav = new ModelAndView("foo/list");
        //model.addAttribute("valor",400);
        return "login.html";
    }

}
//Model model para compartir con la vista