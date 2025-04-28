package com.example.exercicio03.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjetoDTO {
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}