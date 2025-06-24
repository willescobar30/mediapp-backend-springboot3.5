package com.mitocode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

//para interceptar y gestionarcualquier exception que suceda en el proyecto
@RestControllerAdvice
public class ResponseExceptionHandler {

    //interceptando exception de la clase ModelNotFoundException
    @ExceptionHandler(ModelNotFoundException.class)
    //devolviendo estructura customizada del error con la clase CUstomErrorRecord
    public ResponseEntity<CustomErrorRecord> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){

        //devolviendo el error desde la clase Record y mandandole sus 3 argumentos(LocalDateTime, el mensaje y el description con false se hace mas breve de la url)
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        //devolviendo un un error y un codigo de respuesta
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

    }

}
