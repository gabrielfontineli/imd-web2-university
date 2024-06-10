package com.imd.university.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.imd.university.dto.AlunoDTO;
import com.imd.university.model.Aluno;
import com.imd.university.repository.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoService() {
    }

    //create aluno
    public Aluno createAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        Assert.notNull(alunoDTO.dataNascimento(), "Data de nascimento não pode ser nula");
        BeanUtils.copyProperties(alunoDTO, aluno);
        return alunoRepository.save(aluno);
    }

    //get all alunos
    // edit aluno

    public Aluno updateAluno(Long id, AlunoDTO alunoDTO) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(!alunoOptional.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }
        Aluno aluno = alunoOptional.get();
        // check if field is present before copying
        Optional.ofNullable(alunoDTO.nome()).ifPresent(aluno::setNome);
        if (alunoDTO.matricula() != 0) {
            aluno.setMatricula(alunoDTO.matricula());
        }
        Optional.ofNullable(alunoDTO.cpf()).ifPresent(aluno::setCpf);
        Optional.ofNullable(alunoDTO.genero()).ifPresent(aluno::setGenero);
        Optional.ofNullable(alunoDTO.curso()).ifPresent(aluno::setCurso);
        Optional.ofNullable(alunoDTO.dataNascimento()).ifPresent(aluno::setDataNascimento);
        //BeanUtils.copyProperties(alunoDTO, aluno);
        return alunoRepository.save(aluno);
    }
}
