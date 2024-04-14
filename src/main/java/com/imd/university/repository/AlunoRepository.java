package com.imd.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imd.university.model.Aluno; 

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}


