package com.example.medicalapp.medicalapp.controllers;

import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${spring.profiles.active:prod}")
    private String profile;
    
    @GetMapping("/")
    public String getView(Model model,HttpServletResponse response){
        model.addAttribute("isDevMode", "dev".equals(profile));
        response.setStatus(200);
        return "index";
    }

}