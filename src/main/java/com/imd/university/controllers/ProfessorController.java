package com.imd.university.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imd.university.repository.ProfessorRepository;
import com.imd.university.services.ProfessorService;

import jakarta.validation.Valid;

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
    private ProfessorService professorService;

    @PostMapping("create")
    public ResponseEntity<Professor> insertProfessor(@Valid @RequestBody ProfessorDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.createProfessor(entity));
    }
    @GetMapping
    public ResponseEntity<List<Professor>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.listProfessors());
    }

    //testar 
    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.getProfessor(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@RequestBody ProfessorDTO entity, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.updateProfessor(id, entity));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable Long id) {
        if (professorService.deleteProfessor(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
    }

}
