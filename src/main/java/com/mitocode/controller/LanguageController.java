package com.mitocode.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

//creando controller para cambiar de idioma dinamicamente
@RestController
@RequestMapping("/languages")
//inyeccion de dependencias
@RequiredArgsConstructor
public class LanguageController {

    //inyectando LocaleResolver
    private final LocaleResolver localeResolver;
    //importando el request
    private final HttpServletRequest request;
    //importando el response
    private final HttpServletResponse response;

    //endpoint para validar que tipo idioma
    @GetMapping("/locale/{loc}")
    public ResponseEntity<Void> changeLocale(@PathVariable("loc") String loc){
        //almacenando el Locale que selecciono el user en userLocale
        Locale userLocale = switch (loc){
            //para ingles
            case "en", "us" -> Locale.ENGLISH;
            case "fr" -> Locale.FRENCH;
            default -> Locale.ROOT;
        };

        localeResolver.setLocale(request, response, userLocale);

        return ResponseEntity.ok().build();
    }



}
