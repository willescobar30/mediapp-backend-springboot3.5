package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
//asignando nombre especifico a tabla
@Table(name = "user_data")
public class User {

    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;

    //unique=true-> genera una restriccion en la BD(constraint)
    @Column(nullable = false, length = 60, unique = true)
    private String username;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false)
    private boolean enabled;

    //otra forma de hacer realcion de muchos a muchos
    //haciendo relacion desde tabla User a tabla Role
    //fetch sgignifica que traera toda la informacion de user y Roles(funciona como un inner join)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
                joinColumns =  @JoinColumn(name = "id_user", referencedColumnName = "idUser"),
                inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole"))
    private List<Role> roles;





}
