package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultListExamDTO {
    /*esta clase representa todo esto
    {
        "consult" : {
        "patient" : {
            "idPatient" : 1
        },
        "medic" : {
            "idMedic" : 3
        },
        "idUser" : 1,
                "numCunslt" : "C1",
                "consultDate" : "2025-06-24T20:51:20",
                "details" : [
        {"diagnosis" : "FIEBRE", "treatment" : "Paracetamol 500mg"},
        {"diagnosis" : "AMIGDALITIS", "treatment" : "Amoxicilina 500mg"}
        ]

    },
        "lstExam" : [
        {"idExam" : 1 },
        {"idExam" : 2}
    ]
    }*/

    private ConsultDTO consult;
    private List<ExamDTO> lstExam;


}
