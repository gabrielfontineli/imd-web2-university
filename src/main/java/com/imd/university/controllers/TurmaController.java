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
    //get by id
    //post turma
    //put turma
    //delete turma
}
