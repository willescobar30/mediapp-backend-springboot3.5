package com.mitocode.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDTO {

    private Integer idPatient;

    //agregando Jakarta validation constraint
    //para validar que el nombre no vaya vacio
    @NonNull
    //@NotEmpty
    //@NotBlank
    //validando tamanio de los caracteres del nombre y lanzando mensaje de warning
    @Size(min = 3, max= 70, message = "FirstName must have 3 characters")
    private String firstName;
    @NonNull
    @Size(min = 3, max = 70, message = "Lastname must have 3 characters")
    private String lastName;
    @NonNull
    private String dni;
    @NonNull
    private String address;
    @NonNull
    //validando regex para que solo maneje numeros
    @Pattern(regexp = "[0-9]+")
    private String phone;
    @NonNull
    //validando formato de email
    @Email
    private String email;

}
