package com.mitocode.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

//para interceptar y gestionarcualquier exception que suceda en el proyecto
@RestControllerAdvice
//heredando de responseentityhandler permite sobreescribir los metodos necesarios para handlear errores de servicios REST
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    //interceptando exception de la clase ModelNotFoundException
    @ExceptionHandler(ModelNotFoundException.class)
    //devolviendo estructura customizada del error con la clase CUstomErrorRecord
    public ResponseEntity<CustomErrorRecord> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){

        //devolviendo el error desde la clase Record y mandandole sus 3 argumentos(LocalDateTime, el mensaje y el description con false se hace mas breve de la url)
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        //devolviendo un un error y un codigo de respuesta
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    //interceptando excepctions cuando un valor de entrada en el metodo save o update no saean validos(FORMA 2 sobreescribiendo el metodo de la clase heredada)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        //extrayendo y mostrando los mensajes especificos de error de validacion de firstname y lastname
        String msg = ex.getBindingResult().getFieldErrors().stream().
                map(err -> err.getField().concat(":").concat(err.getDefaultMessage())).collect(Collectors.joining(","));

        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), msg, request.getDescription(false));
        //err es la salida del Record haciael servicio Rest
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

//interceptando excepctions cuando un valor de entrada en el metodo save o update no saean validos(FORMA 1)
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    //devolviendo estructura customizada del error con la clase CUstomErrorRecord
    public ResponseEntity<CustomErrorRecord> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){

        //devolviendo el error desde la clase Record y mandandole sus 3 argumentos(LocalDateTime, el mensaje y el description con false se hace mas breve de la url)
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        //devolviendo un un error y un codigo de respuesta
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }*/

    //interceptando excepctions del tipo 500
    @ExceptionHandler(Exception.class)
    //devolviendo estructura customizada del error con la clase CUstomErrorRecord
    public ResponseEntity<CustomErrorRecord> handleDefaulException(Exception ex, WebRequest request){

        //devolviendo el error desde la clase Record y mandandole sus 3 argumentos(LocalDateTime, el mensaje y el description con false se hace mas breve de la url)
        CustomErrorRecord err = new CustomErrorRecord(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));

        //devolviendo un un error y un codigo de respuesta
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
