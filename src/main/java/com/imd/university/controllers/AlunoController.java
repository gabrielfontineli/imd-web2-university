package com.imd.university.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imd.university.repository.AlunoRepository;

import jakarta.validation.Valid;

import com.imd.university.dto.AlunoDTO;
import com.imd.university.model.Aluno;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/create")
    public ResponseEntity<Aluno> createAluno(@Valid @RequestBody AlunoDTO entity) {
        Aluno aluno = new Aluno(entity);
        assert aluno.getDataNascimento() != null: "Data de nascimento não pode ser nula";

        Aluno savedAluno = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAluno);
    }
    @GetMapping
    public ResponseEntity<List<Aluno>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(alunoRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAluno(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(alunoRepository.findById(id).get());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@RequestBody AlunoDTO entity, @PathVariable Long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Aluno aluno = alunoOptional.get();
        Optional.ofNullable(entity.nome()).ifPresent(aluno::setNome);
        if (entity.matricula() != 0) {
            aluno.setMatricula(entity.matricula());
        }
        Optional.ofNullable(entity.cpf()).ifPresent(aluno::setCpf);
        Optional.ofNullable(entity.genero()).ifPresent(aluno::setGenero);
        Optional.ofNullable(entity.curso()).ifPresent(aluno::setCurso);
        Optional.ofNullable(entity.dataNascimento()).ifPresent(aluno::setDataNascimento);

        Aluno updatedAluno = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAluno);
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteAluno(@RequestParam Long id) {
        alunoRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
    }


    
}
