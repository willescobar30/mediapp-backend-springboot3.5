package com.mitocode.service;

import com.mitocode.model.Patient;
import com.mitocode.repo.PatientRepo;

public class PatientService {

    //INSTANCIANDO LA CLASE REPO QUE TRAE LA DATA DE LA BD
    private PatientRepo repo;

    //FUNCION PARA VALIDAR PATIENT VALIDO QUE VIENE DE LA BD
    public Patient validPatient(int idPatient){

        repo = new PatientRepo();
        if(idPatient > 0){
            return repo.getPatientfromBD();
        }else{
            return new Patient(0, "NONE", "NONE");
        }

    }
}
