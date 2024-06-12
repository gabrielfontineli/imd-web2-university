package com.imd.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imd.university.dto.TurmaDTO;
import com.imd.university.model.Turma;
import com.imd.university.services.TurmaService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
        Turma turma = turmaService.getTurma(id)
        .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
        return ResponseEntity.status(HttpStatus.OK).body(turma);
    }
    @PostMapping("/create")
    public ResponseEntity<Turma> insertTurma(@RequestBody TurmaDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.createTurma(entity));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@RequestBody TurmaDTO entity, @RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.updateTurma(id, entity));
    }
    // matricular aluno
    @PostMapping("/enroll/{idTurma}")
    public ResponseEntity<Turma> matricularAluno(@PathVariable Long idTurma, @RequestParam Long idAluno) {
        if (turmaService.matricularAluno(idTurma, idAluno)) {
            Turma turma = turmaService.getTurma(idTurma)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
            return ResponseEntity.status(HttpStatus.OK).body(turma);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @PostMapping("/associate/{idTurma}")
    public ResponseEntity<Turma> adicionarProfessor(@PathVariable Long idTurma, @RequestParam Long idProfessor) {
        if (turmaService.adicionarProfessor(idTurma, idProfessor)) {
            Turma turma = turmaService.getTurma(idTurma)
            .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
            return ResponseEntity.status(HttpStatus.OK).body(turma);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    
    
    //get by id
    //post turma
    //put turma
    //delete turma
}
