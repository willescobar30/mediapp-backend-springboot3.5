package com.mitocode.controller;

import com.mitocode.dto.PatientDTO;
import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final IPatientService service;

    //haciendo inyeccion de dependencias de la clase MapperConfig para modelmapper para evitar hacer inteaccion directa con el Modelo
    private final ModelMapper modelMapper;

    //trayendo todos los pacientes y contorlando respuesta http con ResponseEntity
    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() throws Exception{
        //stream.map funciona como un for para recorrer los elementos del DTO
        //e -> es la iteracion de Patient y la la transforma a PatientDTO
        //usando funcion convertToDto
        List<PatientDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok().body(list);
    }

    //trayendo paciente por id (recuperando id de la url con @PathVariable) y contorlando respuesta http con ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id) throws Exception{
        //devolviendo obejto DTO para evitar hacer inteaccion directa con el Modelo
        PatientDTO obj = convertToDto(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    //guardando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody PatientDTO dto) throws Exception{
        //haciendo proceso contrario de dto a entidad(Patient) con ModelMapper
        Patient obj = service.save(convertToEntity(dto));
        return ResponseEntity.ok().body(modelMapper.map(obj, Patient.class));
    }

    //actualizando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO dto, @PathVariable("id") Integer id) throws Exception{
        //haciendo proceso contrario de dto a entidad(Patient) con ModelMapper
        Patient obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok().body(convertToDto(obj));
    }

    //eliminando un paciente por id y contorlando respuesta http con ResponseEntity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //metodos utilitarios para modelmapper

    //convertir hacia entity
    private Patient convertToEntity(PatientDTO dto){
        return modelMapper.map(dto, Patient.class);
    }

    //convertir hacia dto
    private PatientDTO convertToDto(Patient entity){
        return modelMapper.map(entity, PatientDTO.class);
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
