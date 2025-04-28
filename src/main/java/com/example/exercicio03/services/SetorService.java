package com.example.exercicio03.services;

import com.example.exercicio03.dtos.DadosSetorDTO;
import com.example.exercicio03.dtos.SetorDTO;
import com.example.exercicio03.models.Setor;

import java.util.List;

public interface SetorService {
    void adicionar(SetorDTO setorDTO);
    DadosSetorDTO buscarSetorPorId(Integer idSetor);
    List<Setor> listarTodosComFuncionarios();
}