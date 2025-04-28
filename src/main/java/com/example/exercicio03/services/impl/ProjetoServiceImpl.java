package com.example.exercicio03.services.impl;

import com.example.exercicio03.dtos.DadosProjetoDTO;
import com.example.exercicio03.dtos.FuncionarioDTO;
import com.example.exercicio03.dtos.ProjetoDTO;
import com.example.exercicio03.models.Funcionario;
import com.example.exercicio03.models.Projeto;
import com.example.exercicio03.repositories.FuncionarioRepository;
import com.example.exercicio03.repositories.ProjetoRepository;
import com.example.exercicio03.services.ProjetoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;
    
    @Autowired
    public ProjetoServiceImpl(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }
    
    @Override
    @Transactional
    public void adicionar(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto(
            projetoDTO.getDescricao(),
            projetoDTO.getDataInicio(),
            projetoDTO.getDataFim()
        );
        projetoRepository.save(projeto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository.findByIdWithFuncionarios(id)
            .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com ID: " + id));
        
        List<FuncionarioDTO> funcionariosDTO = projeto.getFuncionarios().stream()
            .map(f -> new FuncionarioDTO(f.getNome(), f.getSetor() != null ? f.getSetor().getId() : null))
            .collect(Collectors.toList());
        
        return new DadosProjetoDTO(
            projeto.getId(),
            projeto.getDescricao(),
            projeto.getDataInicio(),
            projeto.getDataFim(),
            funcionariosDTO
        );
    }
    
    @Override
    @Transactional
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com ID: " + idProjeto));
        
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
            .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + idFuncionario));
        
        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Projeto> buscarProjetosPorDataInicio(LocalDate inicio, LocalDate fim) {
        return projetoRepository.findByDataInicioBetween(inicio, fim);
    }
}