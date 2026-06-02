package com.generation.cadastrorh.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.cadastrorh.model.Funcionario;
import com.generation.cadastrorh.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios") // URL base para este controlador
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // Cadastrar funcionário
    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.salvar(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }

    // listar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Funcionario>> listar() {
        List<Funcionario> lista = funcionarioService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // Pesquisar um funcionário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(funcionario);
    }
}