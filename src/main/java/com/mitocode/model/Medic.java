package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//genera getters, setters, tostring, equalsandhashcode
@Data

//crea un constructor con parametos de la clase
@AllArgsConstructor

//crear un constructor sin parametros de la clase
@NoArgsConstructor
//para que la clase se entienda como una tabla
@Entity
//compara objetos que solo esten determinados en la clase
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idMedic;

    //(relacion entre tabla medico y especialidad) muchos medicos pertenecen a una especialidad -> FK
    @ManyToOne
    //personalizando la columna FK
    @JoinColumn(name = "id_speciality", nullable = false, foreignKey = @ForeignKey(name = "FK_MEDIC_SPECIALITY"))
    private Speciality speciality;
    @Column(nullable = false, length = 70)
    private String firstName;
    @Column(nullable = false, length = 70)
    private String lastName;
    @Column(nullable = false, length = 12)
    private String cmp;
    @Column(length = 200)
    private String photUrl;

}
