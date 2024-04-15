package com.imd.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imd.university.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
}
