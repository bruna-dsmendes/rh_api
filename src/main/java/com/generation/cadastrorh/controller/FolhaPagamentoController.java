package com.generation.cadastrorh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.cadastrorh.model.FolhaPagamento;
import com.generation.cadastrorh.service.FolhaPagamentoService;

@RestController
@RequestMapping("/api/folha")
public class FolhaPagamentoController {

    @Autowired
    private FolhaPagamentoService folhaPagamentoService;

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<FolhaPagamento> emitirFolha(@PathVariable Long id) {
        FolhaPagamento folha = folhaPagamentoService.gerarFolha(id);
        return ResponseEntity.ok(folha);
    }
}