package com.mitocode.service.impl;

import com.mitocode.model.Consult;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IConsultRepo;
import com.mitocode.service.IConsultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//anotacion para que la clase controller pueda acceder a ella
@Service
//Inyeccion de dependencias a traves de constructor(parametros requeridos)
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    private final IConsultRepo repo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return repo;
    }
}
