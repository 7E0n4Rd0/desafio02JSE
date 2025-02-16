package com.devsuperior.demo.controllers;

import com.devsuperior.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CityController {

    @Autowired
    private CityService service;

}
