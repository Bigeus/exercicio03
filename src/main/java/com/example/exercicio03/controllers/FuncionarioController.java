package com.example.exercicio03.controllers;

import com.example.exercicio03.dtos.FuncionarioDTO;
import com.example.exercicio03.models.Projeto;
import com.example.exercicio03.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    
    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    
    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody FuncionarioDTO funcionarioDTO) {
        funcionarioService.adicionar(funcionarioDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("/{idFuncionario}/projetos")
    public ResponseEntity<List<Projeto>> buscarProjetos(@PathVariable Integer idFuncionario) {
        List<Projeto> projetos = funcionarioService.buscarProjetos(idFuncionario);
        return ResponseEntity.ok(projetos);
    }
}