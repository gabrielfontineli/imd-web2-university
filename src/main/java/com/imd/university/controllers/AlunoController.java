package com.imd.university.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imd.university.repository.AlunoRepository;
import com.imd.university.dto.AlunoDTO;
import com.imd.university.model.Aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/create")
    public ResponseEntity<Aluno> createAluno(@RequestBody AlunoDTO entity) {
        Aluno aluno = new Aluno(entity);
        assert aluno.getDataNascimento() != null: "Data de nascimento não pode ser nula";

        Aluno savedAluno = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAluno);
    }
    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoRepository.findAll());
    }
    @GetMapping("/get")
    public ResponseEntity<Aluno> getAluno(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(alunoRepository.findById(id).get());
    }
    @PostMapping("/update")
    public ResponseEntity<Aluno> updateAluno(@RequestBody AlunoDTO entity) {
        Aluno aluno = new Aluno(entity);
        assert aluno.getDataNascimento() != null: "Data de nascimento não pode ser nula";

        Aluno savedAluno = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.OK).body(savedAluno);
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteAluno(@RequestParam Long id) {
        alunoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
    }


    
}
