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
public class ConsultDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDetail;

    //FK tabla Consult
    @ManyToOne
    //personalizando la columna FK
    @JoinColumn(name = "id_consult", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_CONSULT"))
    private Consult consult;
    @Column(nullable = false, length = 70)
    private String diagnosis;
    @Column(nullable = false, length = 300)
    private String treatment;

}
