package com.example.exercicio03.services.impl;

import com.example.exercicio03.dtos.DadosSetorDTO;
import com.example.exercicio03.dtos.FuncionarioDTO;
import com.example.exercicio03.dtos.SetorDTO;
import com.example.exercicio03.models.Setor;
import com.example.exercicio03.repositories.SetorRepository;
import com.example.exercicio03.services.SetorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetorServiceImpl implements SetorService {

    private final SetorRepository setorRepository;
    
    @Autowired
    public SetorServiceImpl(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }
    
    @Override
    @Transactional
    public void adicionar(SetorDTO setorDTO) {
        Setor setor = new Setor(setorDTO.getNome());
        setorRepository.save(setor);
    }
    
    @Override
    @Transactional(readOnly = true)
    public DadosSetorDTO buscarSetorPorId(Integer idSetor) {
        Setor setor = setorRepository.findByIdWithFuncionarios(idSetor)
            .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado com ID: " + idSetor));
        
        List<FuncionarioDTO> funcionariosDTO = setor.getFuncionarios().stream()
            .map(f -> new FuncionarioDTO(f.getNome(), setor.getId()))
            .collect(Collectors.toList());
        
        return new DadosSetorDTO(
            setor.getId(),
            setor.getNome(),
            funcionariosDTO
        );
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Setor> listarTodosComFuncionarios() {
        return setorRepository.findAllWithFuncionarios();
    }
}