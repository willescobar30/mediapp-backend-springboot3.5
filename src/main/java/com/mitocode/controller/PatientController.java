package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//permite hacer un servicio Rest
@RestController

//permite exponer el servicio REST a traves de una url
@RequestMapping("/patients")
public class PatientController {

    //Pide a spring un bean del tipo patientservice(inyeccion de dependencias)
    @Autowired
    private PatientService service;

    @GetMapping
    public Patient sayHello(){
        //ya no es necesario indicar la instancia de forma explicita , porque se uso el Autowired
        //service = new PatientService();

        //llamando funcion de la clse service y pasando id
        return service.validPatient(1);
    }
}
