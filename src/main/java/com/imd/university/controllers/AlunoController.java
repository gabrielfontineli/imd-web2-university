package com.imd.university.controllers;

import com.imd.university.dto.AlunoDTO;
import com.imd.university.model.Aluno;
import com.imd.university.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/create")
    public ResponseEntity<Aluno> insertAluno(@Valid @RequestBody AlunoDTO entity) {
        Aluno createdAluno = alunoService.saveAluno(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAluno);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.listAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findAlunoById(@PathVariable Long id) {
        Aluno aluno = alunoService.getAluno(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@RequestBody AlunoDTO entity, @PathVariable Long id) {
        Aluno updatedAluno = alunoService.editAluno(id, entity);
        return ResponseEntity.ok(updatedAluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAluno(@PathVariable Long id) {
        if (!alunoService.removeAluno(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        return ResponseEntity.ok("Aluno deletado com sucesso");
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<String> disableAluno(@PathVariable Long id) {
        if (!alunoService.desativarAluno(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
        return ResponseEntity.ok("Aluno desativado com sucesso");
    }
}