package com.imd.university.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imd.university.dto.AlunoDTO;
import com.imd.university.model.Aluno;
import com.imd.university.repository.AlunoRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno saveAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        if (alunoRepository.findByCpf(alunoDTO.cpf()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já está em uso");
        }
        
        BeanUtils.copyProperties(alunoDTO, aluno);
        return alunoRepository.save(aluno);
    }
    public Aluno editAluno(Long id, AlunoDTO alunoDTO) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }
        Aluno aluno = alunoOptional.get();

        Optional.ofNullable(alunoDTO.nome()).ifPresent(aluno::setNome);
        Optional.ofNullable(alunoDTO.matricula()).ifPresent(aluno::setMatricula);
        Optional.ofNullable(alunoDTO.cpf()).ifPresent(aluno::setCpf);
        Optional.ofNullable(alunoDTO.genero()).ifPresent(aluno::setGenero);
        Optional.ofNullable(alunoDTO.curso()).ifPresent(aluno::setCurso);
        Optional.ofNullable(alunoDTO.dataNascimento()).ifPresent(aluno::setDataNascimento);
        return alunoRepository.save(aluno);
    }
    public Optional<Aluno> getAluno(long id) {
        return alunoRepository.findById(id);
    }
    public boolean removeAluno(long id) {
        if (alunoRepository.findById(id).isEmpty()) {
            return false;
        }
        alunoRepository.deleteById(id);
        return true;
    }
    public List<Aluno> listAlunos() {
        return alunoRepository.findAll();
    }
    public boolean desativarAluno(long id) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if (alunoOptional.isEmpty()) {
            return false;
        }
        Aluno aluno = alunoOptional.get();
        aluno.desativarAluno();
        alunoRepository.save(aluno);
        return true;
    }
}
