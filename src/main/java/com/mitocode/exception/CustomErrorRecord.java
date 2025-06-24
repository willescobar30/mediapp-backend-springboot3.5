package com.mitocode.exception;

import java.time.LocalDateTime;

//se utilizara para customizar el msj de error
public record CustomErrorRecord(
        //fecha del error
        LocalDateTime datetime,
        //mensaje de error
        String message,
        //detalles del error
        String details


) {


}
