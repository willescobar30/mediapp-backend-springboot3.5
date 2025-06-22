package com.mitocode.service;

import java.util.List;

public interface ICRUD <T, ID>{

    //funcion guardar un registro
    T save(T t) throws Exception;

    //funcion actualizar un registro
    T update(T t, ID id) throws Exception;

    //funcion devolver todos los registros
    List<T> findAll() throws Exception;

    //funcion buscar registro por ID
    T findById(ID id) throws Exception;

    //funcion borrar un registro
    void delete(ID id) throws Exception;
}
