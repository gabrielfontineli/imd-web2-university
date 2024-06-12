package com.imd.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imd.university.model.Turma;
import com.imd.university.services.TurmaService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;
    //get all
    @GetMapping("/")
    public ResponseEntity<List<Turma>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.listTurmas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Turma> getTurma(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.getTurma(id));
    }
    
    //get by id
    //post turma
    //put turma
    //delete turma
}
