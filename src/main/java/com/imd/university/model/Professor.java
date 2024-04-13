package com.imd.university.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    @Id
    private String cpf;
    private int matricula;
    private String departamento;
    private String dataNascimento;
    private Genero genero;
    private float salario;
    private String disciplinaAssociada;

    
}
