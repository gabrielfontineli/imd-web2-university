package com.imd.university.dto;

import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import com.imd.university.model.Genero;

import jakarta.validation.constraints.Positive;

public record AlunoDTO(
    String nome,
    String id,
    @CPF(message = "CPF inválido")
    String cpf,
    LocalDate dataNascimento,
    String curso,
    @Positive(message = "Matrícula inválida")int matricula,
    Genero genero
) {
}

