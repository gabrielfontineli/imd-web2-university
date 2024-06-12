package com.imd.university.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import com.imd.university.dto.AlunoDTO;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "matricula")
    private int matricula;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "genero")
    private Genero genero;

    @Column(name = "curso")
    private String curso;

    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;
    
    @Column(name = "ativo")
    private boolean ativo = true;

    public Aluno(AlunoDTO alunoDTO) {
        this.nome = alunoDTO.nome();
        this.matricula = alunoDTO.matricula();
        this.cpf = alunoDTO.cpf();
        this.genero = alunoDTO.genero();
        this.curso = alunoDTO.curso();
        this.dataNascimento = alunoDTO.dataNascimento();
        this.ativo = true;

    }
    public void ativarAluno() {
        this.ativo = true;
    }
    public void desativarAluno() {
        this.ativo = false;
    }

}

