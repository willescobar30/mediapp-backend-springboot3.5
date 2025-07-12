package com.mitocode.controller;

import com.mitocode.dto.PatientDTO;
import com.mitocode.model.Patient;
import com.mitocode.service.IPatientService;
import com.mitocode.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//permite hacer un servicio Rest
@RestController

//permite exponer el servicio REST a traves de una url
@RequestMapping("/patients")
@RequiredArgsConstructor
//Inyeccion de dependencias a traves de constructor
//@AllArgsConstructor
public class PatientController {

    //Pide a spring un bean del tipo patientservice(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired
    private final IPatientService service;

    //haciendo inyeccion de dependencias de la clase MapperConfig para modelmapper para evitar hacer inteaccion directa con el Modelo
    //QUALIFIER permite hacer inyeccion de dependencias en base a un apodo de un Bean
    //@Qualifier("defaultMapper")
    //private final ModelMapper modelMapper;
    //inyectando a MapperUtil
    private final MapperUtil mapperUtil;

    //trayendo todos los pacientes y contorlando respuesta http con ResponseEntity
    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll() throws Exception{
        //stream.map funciona como un for para recorrer los elementos del DTO
        //e -> es la iteracion de Patient y la la transforma a PatientDTO
        //usando funcion convertToDto
        //List<PatientDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        //usando MapperUtil class
        List<PatientDTO> list = mapperUtil.mapList(service.findAll(), PatientDTO.class);
        return ResponseEntity.ok().body(list);
    }

    //trayendo paciente por id (recuperando id de la url con @PathVariable) y contorlando respuesta http con ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Integer id) throws Exception{
        //devolviendo obejto DTO para evitar hacer inteaccion directa con el Modelo
        //PatientDTO obj = convertToDto(service.findById(id));
        //usando MapperUtil class
        PatientDTO obj = mapperUtil.map(service.findById(id), PatientDTO.class);
        return ResponseEntity.ok().body(obj);
    }

    //guardando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    //@Valid sirve para que los jakarta validation constraint del Patient DTO funcionen
    @PostMapping
    public ResponseEntity<PatientDTO> save(@Valid @RequestBody PatientDTO dto) throws Exception{
        //haciendo proceso contrario de dto a entidad(Patient) con ModelMapper
        //Patient obj = service.save(convertToEntity(dto));
        //usando MapperUtil class
        Patient obj = service.save(mapperUtil.map(dto, Patient.class));
        return ResponseEntity.ok().body(mapperUtil.map(obj, PatientDTO.class));
    }

    //actualizando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO dto, @PathVariable("id") Integer id) throws Exception{
        //haciendo proceso contrario de dto a entidad(Patient) con ModelMapper
        //Patient obj = service.update(convertToEntity(dto), id);
        //usando MapperUtil class
        Patient obj = service.update(mapperUtil.map(dto, Patient.class), id);
        return ResponseEntity.ok().body(mapperUtil.map(obj, PatientDTO.class));
    }

    //eliminando un paciente por id y contorlando respuesta http con ResponseEntity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //creando metodo para funcionalidad hateoas(se debe devolver un EntityModel)
    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
        //recibiendo el obj Patient despues de haber consultado en la BD
        Patient obj = service.findById(id);
        //usando MapperUtil class
        EntityModel<PatientDTO> resource = EntityModel.of(mapperUtil.map(obj, PatientDTO.class));

        //generando link informativo para buscar Patient by id (no se usa el metodo findById solo sirve de referencia)
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatientController.class).findById(obj.getIdPatient()));

        //agregando el link generado al resource
        resource.add(link1.withRel("patient-self-info"));

        return resource;
    }

    //metodos utilitarios para modelmapper

    //convertir hacia entity
   /* private Patient convertToEntity(PatientDTO dto){
        return modelMapper.map(dto, Patient.class);
    }

    //convertir hacia dto
    private PatientDTO convertToDto(Patient entity){
        return modelMapper.map(entity, PatientDTO.class);
    }
    */
    /*
    @GetMapping
    public Patient sayHello(){
        //ya no es necesario indicar la instancia de forma explicita , porque se uso el Autowired
        //service = new PatientServiceImpl();

        //llamando funcion de la clse service y pasando id
        return service.validPatient(1);
    }*/
}
