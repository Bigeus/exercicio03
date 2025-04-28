package com.example.exercicio03.services;

import com.example.exercicio03.dtos.FuncionarioDTO;
import com.example.exercicio03.models.Projeto;

import java.util.List;

public interface FuncionarioService {
    void adicionar(FuncionarioDTO funcionarioDTO);
    List<Projeto> buscarProjetos(Integer idFuncionario);
}