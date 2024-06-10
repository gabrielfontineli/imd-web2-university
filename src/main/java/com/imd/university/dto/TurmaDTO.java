package com.imd.university.dto;

import java.util.List;

import com.imd.university.model.Aluno;
import com.imd.university.model.Professor;

import jakarta.validation.constraints.NotBlank;

public record TurmaDTO(
    @NotBlank(message = "Nome da turma não pode ser vazio")
    String nome,
    String codigo,
    List<Aluno> alunos,
    @NotBlank(message = "Turma deve ter professor responsável")
    Professor professorDisciplina
) {
}
