package com.mitocode.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

//para que pueda usar la clase ConsultExam
@Embeddable
//para que valide los id de Consult y Exam en su momento
@EqualsAndHashCode
public class ConsultExamPK {

    //(relacion entre tabla medico y especialidad) muchos medicos pertenecen a una especialidad -> FK
    @ManyToOne
    //personalizando la columna FK
    @JoinColumn(name = "id_consult", foreignKey = @ForeignKey(name = "FK_CONSULT_EXAM_C"))
    private Consult consult;

    //(relacion entre tabla medico y especialidad) muchos medicos pertenecen a una especialidad -> FK
    @ManyToOne
    //personalizando la columna FK
    @JoinColumn(name = "id_exam", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_EXAM_E"))
    private Exam exam;

}
