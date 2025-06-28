package com.mitocode.config;

import com.mitocode.dto.MedicDTO;
import com.mitocode.model.Medic;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//clase para generar Bean y pueda usarse para generar una sola instancia de ModelMapper

//configuration sirve para registrar beans en spring
@Configuration
public class MapperConfig {

    /*seteando identificador al Bean para ser usado en PatientController y SpecialityController
    ya que ellos usan los mismos nombre de atributos en el DTO y el Modelo*/
    @Bean(name = "defaultMapper")
    //devolviendo una instancia nueva de modelmapper
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    //seteando identificador al Bean para ser usado en MedicController a que este usa diferente nombre de atributos en el DTO y el Modelo por ejemplo el firstName
    @Bean(name = "medicMapper")
    public ModelMapper medicMapper(){
        ModelMapper modelMapper = new ModelMapper();
        //haciendo que los atrubutos del DTO que tienen nombres diferente a los atributos del modelo hagan match
        //estableciendo un relacion desde DTO hacia entity
        //v representa todo lo de Medic DTO  a convertir
        //haciendo match de los atributos del dto y entity
        modelMapper.createTypeMap(MedicDTO.class, Medic.class)
                .addMapping(MedicDTO::getPrimaryName, (dest, v) ->dest.setFirstName((String)v))
                .addMapping(MedicDTO::getSurname, (dest, v) ->dest.setLastName((String)v))
                .addMapping(MedicDTO::getPhoto, (dest, v) ->dest.setPhotUrl((String)v));

        //haciendo transformacion de Entity a DTO para que la data sea visible en el raw y no aparezca NULL
        modelMapper.createTypeMap(Medic.class, MedicDTO.class)
                .addMapping(Medic::getFirstName, (dest, v) ->dest.setPrimaryName((String)v))
                .addMapping(Medic::getLastName, (dest, v) ->dest.setSurname((String)v));

        return modelMapper;
    }


}
