package com.mitocode.service.impl;

import com.mitocode.model.Exam;
import com.mitocode.repo.IExamRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//anotacion para que la clase controller pueda acceder a ella
@Service
//Inyeccion de dependencias a traves de constructor(parametros requeridos)
@RequiredArgsConstructor
public class ExamServiceImpl extends CRUDImpl<Exam, Integer> implements IExamService {

    //Pide a spring un bean del tipo examrepo(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired


    //parametro requerido del constructor xq se usa @requiredargsconstructor(inyeccion de dependencias)
    private final IExamRepo repo;

    @Override
    protected IGenericRepo<Exam, Integer> getRepo() {
        return repo;
    }



    /*
    //sobreescribiendo metodos de la Interfaz IExamService
    @Override
    public Exam save(Exam exam) throws Exception {
        return repo.save(exam);
    }

    @Override
    public Exam update(Exam exam, Integer id) throws Exception {
        //asociando el id que viene el url
        exam.setIdExam(id);
        return repo.save(exam);
    }

    @Override
    public List<Exam> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Exam findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Exam());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
    */
    //parametro opcional
    //private String mesage;

    //FUNCION PARA VALIDAR PATIENT VALIDO QUE VIENE DE LA BD
    /*
    @Override
    public Exam validExam(int idExam){
        //ya no es necesario indicar la instancia de forma explicita , porque se uso el Autowired
        //repo = new ExamRepo();
        if(idExam > 0){
            return repo.getExamfromBD();
        }else{
            return new Exam(0, "NONE", "NONE");
        }

    }*/
}
