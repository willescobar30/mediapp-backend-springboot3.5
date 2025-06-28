package com.mitocode.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityDTO {

    private Integer idSpeciality;
    @NotNull
    private String nameSpeciality;
    @NotNull
    private String descriptionSpeciality;



}
