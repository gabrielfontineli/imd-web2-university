package com.imd.university.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "turmas")
public class Turma {
    public Turma(){
        this.ativo = true;
    }
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String codigo;    
    @ManyToMany
    @JoinTable(
        name = "turma_aluno",
        joinColumns = @jakarta.persistence.JoinColumn(name = "turma_id"),
        inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos;  
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professorDisciplina;
    private boolean ativo;    
    
    public void disableTurma(){
        this.ativo = false;
    }
    public void enableTurma(){
        this.ativo = true;
    }
}

