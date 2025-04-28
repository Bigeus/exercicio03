package com.example.exercicio03.repositories;

import com.example.exercicio03.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    
    @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
    Optional<Projeto> findByIdWithFuncionarios(@Param("id") Integer id);
    
    List<Projeto> findByDataInicioBetween(LocalDate inicio, LocalDate fim);
}