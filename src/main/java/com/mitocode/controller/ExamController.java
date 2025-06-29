package com.mitocode.controller;

import com.mitocode.dto.ExamDTO;
import com.mitocode.model.Exam;
import com.mitocode.service.IExamService;
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
@RequestMapping("/exams")

//Inyeccion de dependencias a traves de constructor
@AllArgsConstructor
public class ExamController {

    //Pide a spring un bean del tipo examservice(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired
    private final IExamService service;

    //haciendo inyeccion de dependencias de la clase MapperConfig para modelmapper para evitar hacer inteaccion directa con el Modelo
    //QUALIFIER permite hacer inyeccion de dependencias en base a un apodo de un Bean
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    //trayendo todos los pacientes y contorlando respuesta http con ResponseEntity
    @GetMapping
    public ResponseEntity<List<ExamDTO>> findAll() throws Exception{
        //stream.map funciona como un for para recorrer los elementos del DTO
        //e -> es la iteracion de Exam y la la transforma a ExamDTO
        //usando funcion convertToDto
        List<ExamDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok().body(list);
    }

    //trayendo paciente por id (recuperando id de la url con @PathVariable) y contorlando respuesta http con ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> findById(@PathVariable("id") Integer id) throws Exception{
        //devolviendo obejto DTO para evitar hacer inteaccion directa con el Modelo
        ExamDTO obj = convertToDto(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    //guardando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    //@Valid sirve para que los jakarta validation constraint del Exam DTO funcionen
    @PostMapping
    public ResponseEntity<Exam> save(@Valid @RequestBody ExamDTO dto) throws Exception{
        //haciendo proceso contrario de dto a entidad(Exam) con ModelMapper
        Exam obj = service.save(convertToEntity(dto));
        return ResponseEntity.ok().body(modelMapper.map(obj, Exam.class));
    }

    //actualizando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> update(@Valid @RequestBody ExamDTO dto, @PathVariable("id") Integer id) throws Exception{
        //haciendo proceso contrario de dto a entidad(Exam) con ModelMapper
        Exam obj = service.update(convertToEntity(dto), id);
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
    public EntityModel<ExamDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
        //recibiendo el obj Exam despues de haber consultado en la BD
        Exam obj = service.findById(id);
        EntityModel<ExamDTO> resource = EntityModel.of(convertToDto(obj));

        //generando link informativo para buscar Exam by id (no se usa el metodo findById solo sirve de referencia)
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ExamController.class).findById(obj.getIdExam()));

        //agregando el link generado al resource
        resource.add(link1.withRel("exam-self-info"));

        return resource;
    }

    //metodos utilitarios para modelmapper

    //convertir hacia entity
    private Exam convertToEntity(ExamDTO dto){
        return modelMapper.map(dto, Exam.class);
    }

    //convertir hacia dto
    private ExamDTO convertToDto(Exam entity){
        return modelMapper.map(entity, ExamDTO.class);
    }

}
