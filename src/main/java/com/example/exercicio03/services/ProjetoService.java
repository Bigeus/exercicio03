package com.example.exercicio03.services;

import com.example.exercicio03.dtos.DadosProjetoDTO;
import com.example.exercicio03.dtos.ProjetoDTO;
import com.example.exercicio03.models.Projeto;

import java.time.LocalDate;
import java.util.List;

public interface ProjetoService {
    void adicionar(ProjetoDTO projetoDTO);
    DadosProjetoDTO buscarProjetoPorId(Integer id);
    void vincularFuncionario(Integer idProjeto, Integer idFuncionario);
    List<Projeto> buscarProjetosPorDataInicio(LocalDate inicio, LocalDate fim);
}