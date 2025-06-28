package com.mitocode.controller;

import com.mitocode.dto.SpecialityDTO;
import com.mitocode.model.Speciality;
import com.mitocode.service.ISpecialityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//permite hacer un servicio Rest
@RestController

//permite exponer el servicio REST a traves de una url
@RequestMapping("/specialities")

//Inyeccion de dependencias a traves de constructor
@AllArgsConstructor
public class SpecialityController {

    //Pide a spring un bean del tipo specialityservice(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired
    private final ISpecialityService service;

    //haciendo inyeccion de dependencias de la clase MapperConfig para modelmapper para evitar hacer inteaccion directa con el Modelo
    //QUALIFIER permite hacer inyeccion de dependencias en base a un apodo de un Bean para ello se debe crear el archivo lombok.config
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    //trayendo todos los pacientes y contorlando respuesta http con ResponseEntity
    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> findAll() throws Exception{
        //stream.map funciona como un for para recorrer los elementos del DTO
        //e -> es la iteracion de Speciality y la la transforma a SpecialityDTO
        //usando funcion convertToDto
        List<SpecialityDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok().body(list);
    }

    //trayendo paciente por id (recuperando id de la url con @PathVariable) y contorlando respuesta http con ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDTO> findById(@PathVariable("id") Integer id) throws Exception{
        //devolviendo obejto DTO para evitar hacer inteaccion directa con el Modelo
        SpecialityDTO obj = convertToDto(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    //guardando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    //@Valid sirve para que los jakarta validation constraint del Speciality DTO funcionen
    @PostMapping
    public ResponseEntity<Speciality> save(@Valid @RequestBody SpecialityDTO dto) throws Exception{
        //haciendo proceso contrario de dto a entidad(Speciality) con ModelMapper
        Speciality obj = service.save(convertToEntity(dto));
        return ResponseEntity.ok().body(modelMapper.map(obj, Speciality.class));
    }

    //actualizando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PutMapping("/{id}")
    public ResponseEntity<SpecialityDTO> update(@Valid @RequestBody SpecialityDTO dto, @PathVariable("id") Integer id) throws Exception{
        //haciendo proceso contrario de dto a entidad(Speciality) con ModelMapper
        Speciality obj = service.update(convertToEntity(dto), id);
        return ResponseEntity.ok().body(convertToDto(obj));
    }

    //eliminando un paciente por id y contorlando respuesta http con ResponseEntity
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //creando metodo para funcionalidad hateoas(se debe devolver un EntityModel)
    @GetMapping("/hateoas/{id}")
    public EntityModel<SpecialityDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
        //recibiendo el obj Speciality despues de haber consultado en la BD
        Speciality obj = service.findById(id);
        EntityModel<SpecialityDTO> resource = EntityModel.of(convertToDto(obj));

        //generando link informativo para buscar Speciality by id (no se usa el metodo findById solo sirve de referencia)
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SpecialityController.class).findById(obj.getIdSpeciality()));

        //agregando el link generado al resource
        resource.add(link1.withRel("speciality-self-info"));

        return resource;
    }

    //metodos utilitarios para modelmapper

    //convertir hacia entity
    private Speciality convertToEntity(SpecialityDTO dto){
        return modelMapper.map(dto, Speciality.class);
    }

    //convertir hacia dto
    private SpecialityDTO convertToDto(Speciality entity){
        return modelMapper.map(entity, SpecialityDTO.class);
    }

}
