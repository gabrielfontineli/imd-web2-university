package com.imd.university.dto;

import java.time.LocalDate;
import org.hibernate.validator.constraints.br.CPF;
import com.imd.university.model.Genero;

public record AlunoDTO(
    String nome,
    String id,
    @CPF(message = "CPF inválido")
    String cpf,
    LocalDate dataNascimento,
    String curso,
    int matricula,
    Genero genero
) {
}

