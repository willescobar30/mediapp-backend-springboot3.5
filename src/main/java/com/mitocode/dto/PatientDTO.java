package com.mitocode.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDTO {

    private Integer idPatient;

    //agregando Jakarta validation constraint
    //para validar que el nombre no vaya vacio
    @NotNull
    //@NotEmpty
    //@NotBlank
    //validando tamanio de los caracteres del nombre y lanzando mensaje de warning
    //leyendo valor de idioma dek messages.properties
    @Size(min = 3, max= 70, message = "{firstname.size}")
    private String firstName;
    @NotNull
    //leyendo valor de idioma dek messages.properties
    @Size(min = 3, max = 70, message = "{lastname.size}")
    private String lastName;
    @NotNull
    private String dni;
    @NotNull
    private String address;
    @NotNull
    //validando regex para que solo maneje numeros
    @Pattern(regexp = "[0-9]+")
    private String phone;
    @NotNull
    //validando formato de email
    @Email
    private String email;

}
