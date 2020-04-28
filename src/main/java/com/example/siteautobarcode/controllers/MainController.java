package com.example.siteautobarcode.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model)
    {
        return "home";
    }
}
