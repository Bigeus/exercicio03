package com.example.exercicio03.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DadosSetorDTO {
    private Integer id;
    private String nome;
    private List<FuncionarioDTO> funcionarios;
}