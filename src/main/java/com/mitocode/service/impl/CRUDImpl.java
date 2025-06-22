package com.mitocode.service.impl;

import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    //CREANDO FUNCION PARA QUE LA INYECCION DE DEPENDENCIAS SEA DINAMICA
    protected abstract IGenericRepo<T, ID> getRepo();
    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        //si no se encuentra un id se establece un valor por defecto
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().deleteById(id);

    }
}
