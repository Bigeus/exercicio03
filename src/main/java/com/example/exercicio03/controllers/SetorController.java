package com.example.exercicio03.controllers;

import com.example.exercicio03.dtos.DadosSetorDTO;
import com.example.exercicio03.dtos.SetorDTO;
import com.example.exercicio03.models.Setor;
import com.example.exercicio03.services.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;
    
    @Autowired
    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }
    
    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody SetorDTO setorDTO) {
        setorService.adicionar(setorDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @GetMapping("/{idSetor}")
    public ResponseEntity<DadosSetorDTO> buscarSetorPorId(@PathVariable Integer idSetor) {
        DadosSetorDTO setor = setorService.buscarSetorPorId(idSetor);
        return ResponseEntity.ok(setor);
    }
    
    @GetMapping
    public ResponseEntity<List<Setor>> listarTodosComFuncionarios() {
        List<Setor> setores = setorService.listarTodosComFuncionarios();
        return ResponseEntity.ok(setores);
    }
}