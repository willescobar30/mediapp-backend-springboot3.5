package com.mitocode.service;

import com.mitocode.model.Consult;
import com.mitocode.model.Exam;

import java.util.List;

public interface IConsultService extends ICRUD<Consult, Integer>{

    //para guardar la consult y detalle de la misma inclyendo examenes
    Consult saveTransactional (Consult consult, List<Exam> exams);


}
