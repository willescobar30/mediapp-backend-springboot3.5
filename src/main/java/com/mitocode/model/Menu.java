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
public class Menu {

    @Id
    @EqualsAndHashCode.Include
    private Integer idMenu;
    @Column(nullable = false, length = 20)
    private String icon;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 50)
    private String url;

    //otra forma de hacer realcion de muchos a muchos
    //haciendo relacion desde tabla Menu a tabla Role
    //fetch sgignifica que traera toda la informacion de Menu y Roles(funciona como un inner join)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "menu_role",
            joinColumns =  @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole"))
    private List<Role> roles;


}
