package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultDTO {

    /*  ESTA CLASE REPRESENTA ESTA ESTRUCTURA
      {
    "consult" : {
        "patient" : {
            "idPatient" : 1
        },
        "medic" : {
            "idMedic" : 3
        },
        "idUser" : 1,
        "numConsult" : "C1",
        "consultDate" : "2025-06-24T20:51:20",
        "details" : [
            {"diagnosis" : "FIEBRE", "treatment" : "Paracetamol 500mg"},
            {"diagnosis" : "AMIGDALITIS", "treatment" : "Amoxicilina 500mg"}
        ]

    },  */

    private Integer idConsult;

    //llave foranea hacia patient
    @NotNull
    private PatientDTO patient;

    //llave foranea hacia Medic
    @NotNull
    private MedicDTO medic;

    //llave fornaea hacia User
    @NotNull
    private Integer idUser;

    @NotNull
    private String numConsult;

    @NotNull
    private LocalDateTime consultDate;

    //Lista de objetos de la clase COnsultDetailDTO
    @NotNull
    @JsonManagedReference  //hace referencia al objeto ConsultDetailDTO  @JsonBackReference
    private List<ConsultDetailDTO> details;



}
