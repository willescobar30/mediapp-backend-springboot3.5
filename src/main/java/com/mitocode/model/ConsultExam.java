package com.mitocode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//genera getters, setters, tostring, equalsandhashcode
@Data

//crea un constructor con parametos de la clase
@AllArgsConstructor

//crear un constructor sin parametros de la clase
@NoArgsConstructor
//para que la clase se entienda como una tabla
@Entity

//para que la clase ConsultExamPK sepa que la llave primaria compuesta esta definida
@IdClass(ConsultExamPK.class)
public class ConsultExam {
    @Id
    private Consult consult;
    @Id
    private Exam exam;

}
