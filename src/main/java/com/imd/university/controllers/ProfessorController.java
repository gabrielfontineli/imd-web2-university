package com.imd.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @PostMapping("/create")
    public ResponseEntity<Professor> insertProfessor(@Valid @RequestBody ProfessorDTO entity) {
        Professor createdProfessor = professorService.createProfessor(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProfessor);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
        List<Professor> professors = professorService.listProfessors();
        return ResponseEntity.ok(professors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findProfessor(@PathVariable Long id) {
        Professor professor = professorService.getProfessor(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));
        return ResponseEntity.ok(professor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@RequestBody ProfessorDTO entity, @PathVariable Long id) {
        Professor updatedProfessor = professorService.editProfessor(id, entity);
        return ResponseEntity.ok(updatedProfessor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable Long id) {
        if (professorService.deleteProfessor(id)) {
            return ResponseEntity.ok("Professor deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<String> disableProfessor(@PathVariable Long id) {
        if (professorService.desativarProfessor(id)) {
            return ResponseEntity.ok("Professor desativado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não encontrado");
    }
}