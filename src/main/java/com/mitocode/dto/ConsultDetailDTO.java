package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultDetailDTO {

    private Integer idDetail;

    //solo sirve de asociacion en el detalle, pero no se maneja como datos de consulta
    @JsonBackReference     // hace referencia al objeto ConsultDTO  @JsonBackReference
    private ConsultDTO consult;

    @NotNull
    private String diagnosis;

    @NotNull
    private String treatment;


}
