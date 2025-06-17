package com.mitocode.controller;

import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //trayendo todos los pacientes y contorlando respuesta http con ResponseEntity
    @GetMapping
    public ResponseEntity<List<Patient>> findAll() throws Exception{
        List<Patient> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //trayendo paciente por id (recuperando id de la url con @PathVariable) y contorlando respuesta http con ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id) throws Exception{
        Patient obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    //guardando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient) throws Exception{
        Patient obj = service.save(patient);
        return ResponseEntity.ok().body(obj);
    }

    //actualizando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PutMapping("{/id}")
    public ResponseEntity<Patient> update(@RequestBody Patient patient, @PathVariable("id") Integer id) throws Exception{
        Patient obj = service.update(patient, id);
        return ResponseEntity.ok().body(obj);
    }

    //eliminando un paciente por id y contorlando respuesta http con ResponseEntity
    @DeleteMapping("{/id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /*
    @GetMapping
    public Patient sayHello(){
        //ya no es necesario indicar la instancia de forma explicita , porque se uso el Autowired
        //service = new PatientServiceImpl();

        //llamando funcion de la clse service y pasando id
        return service.validPatient(1);
    }*/
}
