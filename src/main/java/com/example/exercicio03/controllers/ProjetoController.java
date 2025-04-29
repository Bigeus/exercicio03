package com.example.exercicio03.controllers;

import com.example.exercicio03.dtos.DadosProjetoDTO;
import com.example.exercicio03.dtos.ProjetoDTO;
import com.example.exercicio03.models.Projeto;
import com.example.exercicio03.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;
    
    @Autowired
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }
    
    // Existing methods
    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody ProjetoDTO projetoDTO) {
        projetoService.adicionar(projetoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DadosProjetoDTO> buscarProjetoPorId(@PathVariable Integer id) {
        DadosProjetoDTO projeto = projetoService.buscarProjetoPorId(id);
        return ResponseEntity.ok(projeto);
    }
    
    @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
    public ResponseEntity<Void> vincularFuncionario(
            @PathVariable Integer idProjeto,
            @PathVariable Integer idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/por-periodo")
    public ResponseEntity<List<Projeto>> buscarProjetosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        List<Projeto> projetos = projetoService.buscarProjetosPorDataInicio(dataInicio, dataFim);
        return ResponseEntity.ok(projetos);
    }
}