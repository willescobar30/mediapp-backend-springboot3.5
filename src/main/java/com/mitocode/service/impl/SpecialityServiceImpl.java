package com.mitocode.service.impl;

import com.mitocode.model.Speciality;
import com.mitocode.repo.ISpecialityRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ISpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//anotacion para que la clase controller pueda acceder a ella
@Service
//Inyeccion de dependencias a traves de constructor(parametros requeridos)
@RequiredArgsConstructor
public class SpecialityServiceImpl extends CRUDImpl<Speciality, Integer> implements ISpecialityService {
    
    private final ISpecialityRepo repo;

    @Override
    protected IGenericRepo<Speciality, Integer> getRepo() {
        return repo;
    }
}
