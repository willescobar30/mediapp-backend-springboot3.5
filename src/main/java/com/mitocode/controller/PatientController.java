package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//permite hacer un servicio Rest
@RestController

//permite exponer el servicio REST a traves de una url
@RequestMapping("/patients")

//Inyeccion de dependencias a traves de constructor
@AllArgsConstructor
public class PatientController {

    //Pide a spring un bean del tipo patientservice(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired
    private IPatientService service;

    @GetMapping
    public Patient sayHello(){
        //ya no es necesario indicar la instancia de forma explicita , porque se uso el Autowired
        //service = new PatientServiceImpl();

        //llamando funcion de la clse service y pasando id
        return service.validPatient(1);
    }
}
