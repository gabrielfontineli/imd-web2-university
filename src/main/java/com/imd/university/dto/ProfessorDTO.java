package com.imd.university.dto;

import org.hibernate.validator.constraints.br.CPF;

import com.imd.university.model.Genero;

public record ProfessorDTO(
    String nome,
    @CPF String cpf,
    int matricula,
    String departamento,
    String dataNascimento,
    Genero genero,
    float salario,
    String disciplinaAssociada) {
}  