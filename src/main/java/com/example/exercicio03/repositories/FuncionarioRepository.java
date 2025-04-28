package com.example.exercicio03.repositories;

import com.example.exercicio03.models.Funcionario;
import com.example.exercicio03.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    
    @Query("SELECT f.projetos FROM Funcionario f WHERE f.id = :id")
    List<Projeto> findProjetosByFuncionarioId(@Param("id") Integer id);
}