package com.mitocode.service;

import com.mitocode.model.Patient;
import com.mitocode.repo.IpatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//anotacion para que la clase controller pueda acceder a ella
@Service
//Inyeccion de dependencias a traves de constructor(parametros requeridos)
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService{

    //Pide a spring un bean del tipo patientrepo(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired


    //parametro requerido del constructor xq se usa @requiredargsconstructor
    private final IpatientRepo repo;

    //parametro opcional
    private String mesage;

    //FUNCION PARA VALIDAR PATIENT VALIDO QUE VIENE DE LA BD
    @Override
    public Patient validPatient(int idPatient){
        //ya no es necesario indicar la instancia de forma explicita , porque se uso el Autowired
        //repo = new PatientRepo();
        if(idPatient > 0){
            return repo.getPatientfromBD();
        }else{
            return new Patient(0, "NONE", "NONE");
        }

    }
}
