package com.example.exercicio03.services.impl;

import com.example.exercicio03.dtos.FuncionarioDTO;
import com.example.exercicio03.models.Funcionario;
import com.example.exercicio03.models.Projeto;
import com.example.exercicio03.models.Setor;
import com.example.exercicio03.repositories.FuncionarioRepository;
import com.example.exercicio03.repositories.SetorRepository;
import com.example.exercicio03.services.FuncionarioService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;
    
    @Autowired
    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
    }
    
    @Override
    @Transactional
    public void adicionar(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario(funcionarioDTO.getNome());
        
        if (funcionarioDTO.getSetorId() != null) {
            Setor setor = setorRepository.findById(funcionarioDTO.getSetorId())
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com ID: " + funcionarioDTO.getSetorId()));
            funcionario.setSetor(setor);
        }
        
        funcionarioRepository.save(funcionario);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Projeto> buscarProjetos(Integer idFuncionario) {
        if (!funcionarioRepository.existsById(idFuncionario)) {
            throw new EntityNotFoundException("Funcionário não encontrado com ID: " + idFuncionario);
        }
        return funcionarioRepository.findProjetosByFuncionarioId(idFuncionario);
    }
}