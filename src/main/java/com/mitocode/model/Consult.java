package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
public class Consult {
    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idConsult;

    //FK tabla Patient
    @ManyToOne
    //personalizando la columna FK
    @JoinColumn(name = "id_patient", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_PATIENT"))
    private Patient patient;

    //FK tabla User
    @ManyToOne
    //personalizando la columna FK
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_USER"))
    private User user;

    //FK tabla Medic
    @ManyToOne
    //personalizando la columna FK
    @JoinColumn(name = "id_medic", nullable = false, foreignKey = @ForeignKey(name = "FK_CONSULT_MEDIC"))
    private Medic medic;

    @Column(nullable = false, length = 3)
    private String numConsult;
    @Column(nullable = false)
    private LocalDateTime consultDate;

    //una consulta puede tener muchos detalles
    //"consult" es el objeto que representa la llave foranea en la clase ConsultDetail"
    //cascade -> permite mantener la integridad de lo que pase con el encabezado(Consult) pase con el detalle(ConsultDetail)
    @OneToMany(mappedBy = "consult", cascade = CascadeType.ALL)
    private List<ConsultDetail> details;




}
