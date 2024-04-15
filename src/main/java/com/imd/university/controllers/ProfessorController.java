package com.imd.university.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imd.university.repository.ProfessorRepository;
import com.imd.university.dto.ProfessorDTO;
import com.imd.university.model.Professor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping("create")
    public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorDTO entity) {
        Professor professor = new Professor(entity);
        Professor savedProfessor = professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfessor);
    }
    @GetMapping
    public ResponseEntity<List<Professor>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(professorRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professorRepository.findById(id).get());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@RequestBody ProfessorDTO entity, @PathVariable Long id) {
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Professor professor = professorOptional.get();
        Optional.ofNullable(entity.nome()).ifPresent(professor::setNome);
        if (entity.matricula() != 0) {
            professor.setMatricula(entity.matricula());
        }
        if (entity.departamento() != null) {
            professor.setDepartamento(entity.departamento());
        }
        if (entity.dataNascimento() != null) {
            professor.setDataNascimento(entity.dataNascimento());
        }
        if (entity.genero() != null) {
            professor.setGenero(entity.genero());
        }
        if (entity.salario() != 0) {
            professor.setSalario(entity.salario());
        }
        if (entity.disciplinaAssociada() != null) {
            professor.setDisciplinaAssociada(entity.disciplinaAssociada());
        }
        Professor updatedProfessor = professorRepository.save(professor);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProfessor);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable Long id) {
        professorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso");
    }

}
