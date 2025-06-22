package com.mitocode.repo;

import com.mitocode.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

//heredando IGenericRepo
public interface IExamRepo extends JpaRepository<Exam, Integer> {
}
