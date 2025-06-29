package com.mitocode.repo;

import com.mitocode.model.ConsultExam;
import com.mitocode.model.ConsultExamPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//seteando el tipo como el (@IdClass(ConsultExamPK.class)) de la clase del modelo ConsultExam
public interface IConsultExamRepo extends IGenericRepo<ConsultExam, ConsultExamPK>{

    @Modifying //si se quiere hacer INSERT, UPDATE o DELETE ageregar @Modifying para que @Query funcione

    //hacinedo query nativo tal y como sea hace en un motor de BD
    //asi se pasan parametros en spring data JPA ->  :idConsult y :idExam
    @Query(value = "INSERT INTO consult_exam(id_consult, id_exam) VALUES(:idConsult, :idExam)", nativeQuery = true)
    //seteando integer para que devuelva el numero de filas afectadas al momento de hacer el insert
    //@Param es para pasar los valores de VALUES
    Integer saveExam(@Param("idConsult") Integer idConsult, @Param("idExam") Integer idExam);
}
