package com.generation.cadastrorh.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.cadastrorh.model.Departamento;
import com.generation.cadastrorh.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos") // URL base para este controlador
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    // Criar departamentos
    @PostMapping
    public ResponseEntity<Departamento> criar(@RequestBody Departamento departamento) {
        Departamento novoDepartamento = departamentoService.salvar(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDepartamento);
    }

    // Listar todos os departamentos
    @GetMapping
    public ResponseEntity<List<Departamento>> listar() {
        List<Departamento> lista = departamentoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // Procurar um departamento por ID
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        Departamento departamento = departamentoService.buscarPorId(id);
        return ResponseEntity.ok(departamento);
    }
    
    // Atualiza o departamento
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> atualizar(@PathVariable Long id, @RequestBody Departamento departamento) {
        Departamento atualizado = departamentoService.atualizar(id, departamento);
        return ResponseEntity.ok(atualizado);
    }
    // Deleta o departamento 
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        departamentoService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna o status 204 No Content
    }
}
