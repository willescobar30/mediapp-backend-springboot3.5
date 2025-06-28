package com.mitocode.controller;

import com.mitocode.dto.MedicDTO;
import com.mitocode.model.Medic;
import com.mitocode.service.IMedicService;
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
@RequestMapping("/medics")

//Inyeccion de dependencias a traves de constructor
@AllArgsConstructor
public class MedicController {

    //Pide a spring un bean del tipo medicservice(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired
    private final IMedicService service;

    //haciendo inyeccion de dependencias de la clase MapperConfig para modelmapper para evitar hacer inteaccion directa con el Modelo
    //QUALIFIER permite hacer inyeccion de dependencias en base a un apodo de un Bean
    @Qualifier("medicMapper")
    private final ModelMapper modelMapper;

    //trayendo todos los pacientes y contorlando respuesta http con ResponseEntity
    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll() throws Exception{
        //stream.map funciona como un for para recorrer los elementos del DTO
        //e -> es la iteracion de Medic y la la transforma a MedicDTO
        //usando funcion convertToDto
        List<MedicDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok().body(list);
    }

    //trayendo paciente por id (recuperando id de la url con @PathVariable) y contorlando respuesta http con ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id) throws Exception{
        //devolviendo obejto DTO para evitar hacer inteaccion directa con el Modelo
        MedicDTO obj = convertToDto(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    //guardando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    //@Valid sirve para que los jakarta validation constraint del Medic DTO funcionen
    @PostMapping
    public ResponseEntity<Medic> save(@Valid @RequestBody MedicDTO dto) throws Exception{
        //haciendo proceso contrario de dto a entidad(Medic) con ModelMapper
        Medic obj = service.save(convertToEntity(dto));
        return ResponseEntity.ok().body(modelMapper.map(obj, Medic.class));
    }

    //actualizando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PutMapping("/{id}")
    public ResponseEntity<MedicDTO> update(@Valid @RequestBody MedicDTO dto, @PathVariable("id") Integer id) throws Exception{
        //haciendo proceso contrario de dto a entidad(Medic) con ModelMapper
        Medic obj = service.update(convertToEntity(dto), id);
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
    public EntityModel<MedicDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
        //recibiendo el obj Medic despues de haber consultado en la BD
        Medic obj = service.findById(id);
        EntityModel<MedicDTO> resource = EntityModel.of(convertToDto(obj));

        //generando link informativo para buscar Medic by id (no se usa el metodo findById solo sirve de referencia)
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MedicController.class).findById(obj.getIdMedic()));

        //agregando el link generado al resource
        resource.add(link1.withRel("medic-self-info"));

        return resource;
    }

    //metodos utilitarios para modelmapper

    //convertir hacia entity
    private Medic convertToEntity(MedicDTO dto){
        return modelMapper.map(dto, Medic.class);
    }

    //convertir hacia dto
    private MedicDTO convertToDto(Medic entity){
        return modelMapper.map(entity, MedicDTO.class);
    }

}
