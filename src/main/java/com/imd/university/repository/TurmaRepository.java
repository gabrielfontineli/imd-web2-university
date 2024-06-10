package com.imd.university.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imd.university.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long>{
}
