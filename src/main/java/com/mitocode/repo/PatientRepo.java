package com.mitocode.repo;

import com.mitocode.model.Patient;

public class PatientRepo {

    //funcion que trae datos de la BD
    public Patient getPatientfromBD(){
        return new Patient(1, "William", "Rivas");
    }

}
