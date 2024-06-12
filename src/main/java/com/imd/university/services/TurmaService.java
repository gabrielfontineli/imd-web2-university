package com.imd.university.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imd.university.dto.TurmaDTO;
import com.imd.university.model.Aluno;
import com.imd.university.model.Professor;
import com.imd.university.model.Turma;
import com.imd.university.repository.TurmaRepository;

import lombok.NoArgsConstructor;


@Service
@NoArgsConstructor


public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired ProfessorService professorService;

    public TurmaService(TurmaRepository turmaRepository, AlunoService alunoService) {
        this.turmaRepository = turmaRepository;
        this.alunoService = alunoService;
    }

    public List<Turma> listTurmas() {
        return turmaRepository.findAll();
    }
    // get turma by id
    
    public Optional<Turma> getTurma(long id) {
        return turmaRepository.findById(id);
    }
    // save turma
    
    public Turma createTurma(TurmaDTO turmaDTO) {
        Turma turma = new Turma();
        BeanUtils.copyProperties(turmaDTO, turma);
        return turmaRepository.save(turma);
    }

    // update turma
    
    public Turma updateTurma(Long id, TurmaDTO turmaDTO) {
        Optional<Turma> turmaOptional = getTurma(id);
        if(!turmaOptional.isPresent()) {
            return null;
        }
        Turma turma = turmaOptional.get();
        BeanUtils.copyProperties(turmaDTO, turma);
        return turmaRepository.save(turma);
    }
    // delete turma
    
    public boolean deleteTurma(Long id) {
        Optional<Turma> turmaOptional = getTurma(id);
        if(turmaOptional.isEmpty()) {
            return false;
        }
        turmaRepository.deleteById(id);
        return true;
    }
    
    // delete logic
    public boolean deleteTurmaLogic(Long id){
        Optional<Turma> turmaOptional = getTurma(id);
        if(turmaOptional.isEmpty()) {
            return false;
        }
        Turma turma = turmaOptional.get();
        turma.disableTurma();
        turmaRepository.save(turma);
        return true;
    }
    // matricular aluno
   public boolean matricularAluno(Long id, Long alunoId){
        Optional<Turma> turmaOptional = getTurma(id);
        if(turmaOptional.isEmpty()) {
            return false;
        }
        Turma turma = turmaOptional.get();
        Optional<Aluno> alunoOptional = alunoService.getAluno(alunoId);
        if(alunoOptional.isEmpty()) {
            return false;
        }
        Aluno aluno = alunoOptional.get();
        turma.getAlunos().add(aluno);
        turmaRepository.save(turma);
        return true;
   }
    // desmatricular aluno
    public boolean desmatricularAluno(Long id, Long alunoId){
        Optional<Turma> turmaOptional = getTurma(id);
        if(turmaOptional.isEmpty()) {
            return false;
        }
        Turma turma = turmaOptional.get();
        Optional<Aluno> alunoOptional = alunoService.getAluno(alunoId);
        if(alunoOptional.isEmpty()) {
            return false;
        }
        Aluno aluno = alunoOptional.get();
        turma.getAlunos().remove(aluno);
        turmaRepository.save(turma);
        return true;
    }
    // adicionar professor
    public boolean adicionarProfessor(Long id, Long professorId){
        Optional<Turma> turmaOptional = getTurma(id);
        if(turmaOptional.isEmpty()) {
            return false;
        }
        Turma turma = turmaOptional.get();
        Optional<Professor> professorOptional = professorService.getProfessor(professorId);
        if(professorOptional.isEmpty()) {
            return false;
        }
        Professor professor = professorOptional.get();
        turma.setProfessorDisciplina(professor);
        turmaRepository.save(turma);
        return true;
    }
    // remover professor

    public boolean removerProfessor(Long id, Long professorId){
        Optional<Turma> turmaOptional = getTurma(id);
        if(turmaOptional.isEmpty()) {
            return false;
        }
        Turma turma = turmaOptional.get();
        turma.setProfessorDisciplina(null);
        turmaRepository.save(turma);
        return true;
    }
 
    
}