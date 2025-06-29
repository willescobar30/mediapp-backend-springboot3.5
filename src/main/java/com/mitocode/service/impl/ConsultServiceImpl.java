package com.mitocode.service.impl;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;
import com.mitocode.repo.IConsultExamRepo;
import com.mitocode.repo.IConsultRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IConsultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//anotacion para que la clase controller pueda acceder a ella
@Service
//Inyeccion de dependencias a traves de constructor(parametros requeridos)
@RequiredArgsConstructor
public class ConsultServiceImpl extends CRUDImpl<Consult, Integer> implements IConsultService {

    private final IConsultRepo consultRepo;
    //creando repo consultexamrepo
    private final IConsultExamRepo consultExamRepo;

    @Override
    protected IGenericRepo<Consult, Integer> getRepo() {
        return consultRepo;
    }

    //implementando saveTransactional de la interfaz IConsultService

    @Transactional //para hacer el commit de la Query de la interfaz IConsultExamRepo y no deje hacer insert si algo falla
    @Override
    public Consult saveTransactional(Consult consult, List<Exam> exams) {
        //aqui se hara la logica de insert para las tablas:
        // consult;
        //consult_detail;
        //consult_exam;

         //guardando datos en tablas consult y consult_detail
        consultRepo.save(consult);
        //guardando listado de examenes para la consulta(tabla consult_exam)
        //para cada examen que trae la lista se llama a un repo para guardar basado en id de consulta y id de examen
        exams.forEach(exam -> consultExamRepo.saveExam(consult.getIdConsult(), exam.getIdExam()));
        return null;
    }
}
