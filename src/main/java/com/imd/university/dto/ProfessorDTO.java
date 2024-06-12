package com.imd.university.dto;

import org.hibernate.validator.constraints.br.CPF;
import com.imd.university.model.Genero;
import jakarta.validation.constraints.Positive;

public record ProfessorDTO(
    String nome,
    @CPF String cpf,
    @Positive int matricula,
    String departamento,
    String dataNascimento,
    Genero genero,
    @Positive float salario,
    String disciplinaAssociada) {
}  