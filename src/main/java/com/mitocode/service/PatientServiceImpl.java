package com.mitocode.service;

import com.mitocode.model.Patient;
import com.mitocode.repo.PatientRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//anotacion para que la clase controller pueda acceder a ella
@Service
public class PatientServiceImpl implements IPatientService{

    //Pide a spring un bean del tipo patientrepo(inyeccion de dependencias)
    @Autowired
    private PatientRepoImpl repo;

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
