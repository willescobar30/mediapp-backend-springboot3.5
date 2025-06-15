package com.mitocode.repo;

import com.mitocode.model.Patient;
import org.springframework.stereotype.Repository;

//anotacion para que la clase service pueda acceder a ella
@Repository
public class PatientRepoImpl implements IpatientRepo{

    //funcion que trae datos de la BD
    @Override
    public Patient getPatientfromBD(){
        return new Patient(1, "William", "Rivas");
    }

}
