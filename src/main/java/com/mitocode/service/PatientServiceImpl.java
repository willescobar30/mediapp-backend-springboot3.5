package com.mitocode.service;

import com.mitocode.model.Patient;
import com.mitocode.repo.IpatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//anotacion para que la clase controller pueda acceder a ella
@Service
//Inyeccion de dependencias a traves de constructor(parametros requeridos)
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService{

    //Pide a spring un bean del tipo patientrepo(inyeccion de dependencias)
    //Tambien se puede acceder al bean de Repository atraves de la interfaz usando autowired(inyeccion de dependencias)
    //@Autowired


    //parametro requerido del constructor xq se usa @requiredargsconstructor(inyeccion de dependencias)
    private final IpatientRepo repo;

    //sobreescribiendo metodos de la Interfaz IPatientService
    @Override
    public Patient save(Patient patient) throws Exception {
        return repo.save(patient);
    }

    @Override
    public Patient update(Patient patient, Integer id) throws Exception {
        //asociando el id que viene el url
        patient.setIdPatient(id);
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Patient findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Patient());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }

    //parametro opcional
    //private String mesage;

    //FUNCION PARA VALIDAR PATIENT VALIDO QUE VIENE DE LA BD
    /*
    @Override
    public Patient validPatient(int idPatient){
        //ya no es necesario indicar la instancia de forma explicita , porque se uso el Autowired
        //repo = new PatientRepo();
        if(idPatient > 0){
            return repo.getPatientfromBD();
        }else{
            return new Patient(0, "NONE", "NONE");
        }

    }*/
}
