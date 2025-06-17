package com.mitocode.repo;

import com.mitocode.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpatientRepo extends JpaRepository<Patient, Integer> {

    //Patient getPatientfromBD();

}
