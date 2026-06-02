package com.generation.cadastrorh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.cadastrorh.model.Funcionario;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
  
    // Buscar funcionário por CPF
    Optional<Funcionario> findByCpf(String cpf);
    
    // Buscar funcionário por E-mail
    Optional<Funcionario> findByEmail(String email);
}