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

public class Patient {

    //llave primaria
    @Id
    //para que la llave primaria sea incremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //este campo va a comparar con otro objeto del tipo Paciente
    @EqualsAndHashCode.Include
    private Integer idPatient;
    @Column(nullable = false, length = 70)
    private String firstName;
    @Column(nullable = false, length = 70)
    private String lastName;
    @Column(nullable = false, length = 8)
    private String dni;
    @Column(length = 70)
    private String address;
    @Column(nullable = false, length = 9)
    private String phone;
    @Column(nullable = false, length = 55)
    private String email;


}
