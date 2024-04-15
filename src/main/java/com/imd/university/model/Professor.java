package com.imd.university.model;

import com.imd.university.dto.ProfessorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Column(name = "matricula")
    private int matricula;
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "dataNascimento", nullable = false)
    private String dataNascimento;
    @Column(name = "genero")
    private Genero genero;
    @Column(name = "salario")
    private float salario;
    @Column(name = "disciplinaAssociada")
    private String disciplinaAssociada;

    public Professor() {
    }

    public Professor(ProfessorDTO professorDTO) {
        this.nome = professorDTO.nome();
        this.cpf = professorDTO.cpf();
        this.matricula = professorDTO.matricula();
        this.departamento = professorDTO.departamento();
        this.dataNascimento = professorDTO.dataNascimento();
        this.genero = professorDTO.genero();
        this.salario = professorDTO.salario();
        this.disciplinaAssociada = professorDTO.disciplinaAssociada();
    }
}

