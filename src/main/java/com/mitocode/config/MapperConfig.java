package com.mitocode.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//clase para generar Bean y pueda usarse para generar una sola instancia de ModelMapper

//configuration sirve para registrar beans en spring
@Configuration
public class MapperConfig {

    @Bean
    //devolviendo una instancia nueva de modelmapper
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
