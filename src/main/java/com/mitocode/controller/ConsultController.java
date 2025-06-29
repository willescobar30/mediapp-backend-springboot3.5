package com.mitocode.controller;

import com.mitocode.dto.ConsultDTO;
import com.mitocode.dto.ConsultListExamDTO;
import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.service.IConsultService;
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
@RequestMapping("/consults")

//Inyeccion de dependencias a traves de constructor
@AllArgsConstructor
public class ConsultController {

    //Pide a spring un bean del tipo consultservice(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired
    private final IConsultService service;

    //haciendo inyeccion de dependencias de la clase MapperConfig para modelmapper para evitar hacer inteaccion directa con el Modelo
    //QUALIFIER permite hacer inyeccion de dependencias en base a un apodo de un Bean
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;

    //trayendo todos los pacientes y contorlando respuesta http con ResponseEntity
    @GetMapping
    public ResponseEntity<List<ConsultDTO>> findAll() throws Exception{
        //stream.map funciona como un for para recorrer los elementos del DTO
        //e -> es la iteracion de Consult y la la transforma a ConsultDTO
        //usando funcion convertToDto
        List<ConsultDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok().body(list);
    }

    //trayendo paciente por id (recuperando id de la url con @PathVariable) y contorlando respuesta http con ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<ConsultDTO> findById(@PathVariable("id") Integer id) throws Exception{
        //devolviendo obejto DTO para evitar hacer inteaccion directa con el Modelo
        ConsultDTO obj = convertToDto(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    //guardando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    //@Valid sirve para que los jakarta validation constraint del Consult DTO funcionen
    //usando consultlistExamnDTO ya que se mostrara su consult y detalle(lista de examenes y details)
    @PostMapping
    public ResponseEntity<Consult> save(@Valid @RequestBody ConsultListExamDTO dto) throws Exception{

        //recuperando la consulta y consult details
        Consult obj1 = convertToEntity(dto.getConsult());
        //recuperando listado de examenes que vienen en el json y transformandolo a tipo Exam
        List<Exam> list = dto.getLstExam().stream().map(examDTO -> modelMapper.map(examDTO, Exam.class)).toList();

        //haciendo proceso contrario de dto a entidad(Consult) con ModelMapper
        //mandando el obj1 y la lista de examenes al service para que sean guardados
        Consult obj = service.saveTransactional(obj1, list);
        return ResponseEntity.ok().body(modelMapper.map(obj, Consult.class));
    }

    //actualizando un paciente nuevo y contorlando respuesta http con ResponseEntity, usando @RequestBody para que data que se mande haga match con la clase Modelo
    @PutMapping("/{id}")
    public ResponseEntity<ConsultDTO> update(@Valid @RequestBody ConsultDTO dto, @PathVariable("id") Integer id) throws Exception{
        //haciendo proceso contrario de dto a entidad(Consult) con ModelMapper
        Consult obj = service.update(convertToEntity(dto), id);
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
    public EntityModel<ConsultDTO> findByIdHateoas(@PathVariable("id") Integer id) throws Exception{
        //recibiendo el obj Consult despues de haber consultado en la BD
        Consult obj = service.findById(id);
        EntityModel<ConsultDTO> resource = EntityModel.of(convertToDto(obj));

        //generando link informativo para buscar Consult by id (no se usa el metodo findById solo sirve de referencia)
        WebMvcLinkBuilder link1 = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ConsultController.class).findById(obj.getIdConsult()));

        //agregando el link generado al resource
        resource.add(link1.withRel("consult-self-info"));

        return resource;
    }

    //metodos utilitarios para modelmapper

    //convertir hacia entity
    private Consult convertToEntity(ConsultDTO dto){
        return modelMapper.map(dto, Consult.class);
    }

    //convertir hacia dto
    private ConsultDTO convertToDto(Consult entity){
        return modelMapper.map(entity, ConsultDTO.class);
    }

}
