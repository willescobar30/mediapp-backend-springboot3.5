package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicDTO {

    //los atributos en el DTO pueden llevar nombre diferente que en el modelo
    private Integer idMedic;

    //usando un entero para generar una instancia de Speciality y se posicione como una FK
    //en la clase modelo esto es un objeto del tipo Speciality
    private Integer idSpeciality;
    private String primaryName;
    private String surname;
    private String cmpMedic;
    private String photo;


}
