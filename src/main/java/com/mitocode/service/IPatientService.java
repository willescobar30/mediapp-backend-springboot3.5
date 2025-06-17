package com.mitocode.service;

import com.mitocode.model.Patient;

import java.util.List;

public interface IPatientService {

    //funcion guardar un paciente
    Patient save(Patient patient) throws Exception;

    //funcion actualizar un Paciente
    Patient update(Patient patient, Integer id) throws Exception;

    //funcion devolver todos los pacientes
    List<Patient> findAll() throws Exception;

    //funcion buscar paciente por ID
    Patient findById(Integer id) throws Exception;

    //funcion borrar un paciente
    void delete(Integer id) throws Exception;

    //Patient validPatient(int idPatient);
}
