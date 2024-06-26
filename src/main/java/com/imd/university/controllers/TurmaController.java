package com.imd.university.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @GetMapping("/")
    public ResponseEntity<List<Turma>> getAll() {
        List<Turma> turmas = turmaService.listTurmas();
        return ResponseEntity.ok(turmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findTurma(@PathVariable Long id) {
        Turma turma = turmaService.getTurma(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
        return ResponseEntity.ok(turma);
    }

    @PostMapping("/create")
    public ResponseEntity<Turma> insertTurma(@RequestBody TurmaDTO entity) {
        Turma createdTurma = turmaService.saveTurma(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTurma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> updateTurma(@RequestBody TurmaDTO entity, @PathVariable Long id) {
        Turma updatedTurma = turmaService.editTurma(id, entity);
        return ResponseEntity.ok(updatedTurma);
    }

    @PostMapping("/enroll/{idTurma}")
    public ResponseEntity<Turma> matricularAluno(@PathVariable Long idTurma, @RequestParam Long idAluno) {
        if (turmaService.matricularAluno(idTurma, idAluno)) {
            Turma turma = turmaService.getTurma(idTurma)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
            return ResponseEntity.ok(turma);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/disenroll/{idTurma}")
    public ResponseEntity<Turma> desmatricularAluno(@PathVariable Long idTurma, @RequestParam Long idAluno) {
        if (turmaService.desmatricularAluno(idTurma, idAluno)) {
            Turma turma = turmaService.getTurma(idTurma)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
            return ResponseEntity.ok(turma);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/associate/{idTurma}")
    public ResponseEntity<Turma> adicionarProfessor(@PathVariable Long idTurma, @RequestParam Long idProfessor) {
        if (turmaService.adicionarProfessor(idTurma, idProfessor)) {
            Turma turma = turmaService.getTurma(idTurma)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
            return ResponseEntity.ok(turma);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/disassociate/{idTurma}")
    public ResponseEntity<Turma> removerProfessor(@PathVariable Long idTurma, @RequestParam Long idProfessor) {
        if (turmaService.removerProfessor(idTurma, idProfessor)) {
            Turma turma = turmaService.getTurma(idTurma)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));
            return ResponseEntity.ok(turma);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        if (turmaService.removeTurma(id)) {
            return ResponseEntity.ok("Turma deletada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
    }

    @DeleteMapping("/disable/{id}")
    public ResponseEntity<String> disableOne(@PathVariable Long id) {
        if (turmaService.disableTurma(id)) {
            return ResponseEntity.ok("Turma desativada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não encontrada");
    }
}