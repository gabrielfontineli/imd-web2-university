package com.imd.university.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imd.university.dto.ProfessorDTO;
import com.imd.university.model.Professor;
import com.imd.university.repository.ProfessorRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor createProfessor(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        BeanUtils.copyProperties(professorDTO, professor);
        return professorRepository.save(professor);
    }
    public List<Professor> listProfessors() {
        return professorRepository.findAll();
    }
    public Optional<Professor> getProfessor(Long id) {
        return professorRepository.findById(id);
    }
    public Professor editProfessor(Long id, ProfessorDTO professorDTO) {
        Optional<Professor> isProfessor = professorRepository.findById(id);
        if (isProfessor.isEmpty()) {
            throw new RuntimeException("Professor não encontrado");
        }
        Professor existingProfessor = isProfessor.get();

        Optional.ofNullable(professorDTO.nome()).ifPresent(existingProfessor::setNome);
        Optional.ofNullable(professorDTO.matricula()).ifPresent(existingProfessor::setMatricula);
        Optional.ofNullable(professorDTO.cpf()).ifPresent(existingProfessor::setCpf);
        Optional.ofNullable(professorDTO.departamento()).ifPresent(existingProfessor::setDepartamento);
        Optional.ofNullable(professorDTO.dataNascimento()).ifPresent(existingProfessor::setDataNascimento);
        Optional.ofNullable(professorDTO.genero()).ifPresent(existingProfessor::setGenero);
        Optional.ofNullable(professorDTO.salario()).ifPresent(existingProfessor::setSalario);
        Optional.ofNullable(professorDTO.disciplinaAssociada()).ifPresent(existingProfessor::setDisciplinaAssociada);

        return professorRepository.save(existingProfessor);
    }
    public boolean deleteProfessor(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean desativarProfessor(long id){
        Optional<Professor> professor = getProfessor(id);
        if(professor.isEmpty()){
            return false;
        }
        Professor professorDesativado = professor.get();
        professorDesativado.setAtivo(false);
        professorRepository.save(professorDesativado);
        return true;
    }
    
    
}
