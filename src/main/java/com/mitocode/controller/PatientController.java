package com.mitocode.controller;

import com.mitocode.model.Patient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//permite hacer un servicio Rest
@RestController

//permite exponer el servicio REST a traves de una url
@RequestMapping("/patients")
public class PatientController {

    @GetMapping
    public Patient sayHello(){
        //inicializando constructor de la clase Patient y enviando sus valores requeridos
        return new Patient(1, "Will","Rivas");

    }
}
