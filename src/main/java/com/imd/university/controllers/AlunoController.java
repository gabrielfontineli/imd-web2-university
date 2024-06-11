package com.imd.university.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.imd.university.services.AlunoService;
import com.imd.university.dto.AlunoDTO;
import com.imd.university.model.Aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/create")
    public ResponseEntity<Aluno> createAluno(@Valid @RequestBody AlunoDTO entity) {
        Aluno createdAluno = alunoService.createAluno(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.listAlunos();
        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findOne(@PathVariable Long id) {
        Aluno aluno = alunoService.getAluno(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@RequestBody AlunoDTO entity, @PathVariable Long id) {
        Aluno updatedAluno = alunoService.updateAluno(id, entity);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAluno);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAluno(@RequestParam Long id) {
        if (!alunoService.deleteAluno(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
    }
}