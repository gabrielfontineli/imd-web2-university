package com.imd.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Professor> insertOne(@Valid @RequestBody ProfessorDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.createProfessor(entity));
    }
    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.listProfessors());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Professor> findOne(@PathVariable Long id) {
        Professor professor = professorService.getProfessor(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateOne(@RequestBody ProfessorDTO entity, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.updateProfessor(id, entity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        if (professorService.deleteProfessor(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
    }
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<String> disableOne(@PathVariable Long id) {
        if (professorService.desativarProfessor(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Professor desativado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
    }

}
