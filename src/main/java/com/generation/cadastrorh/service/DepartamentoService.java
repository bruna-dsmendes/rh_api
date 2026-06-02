package com.generation.cadastrorh.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.cadastrorh.model.Departamento;
import com.generation.cadastrorh.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento salvar(Departamento departamento) {
        // Validar se a sigla já existe
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }
    
// listar todos os departamentos
    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado com o ID: " + id));
    }
    
    public Departamento atualizar(Long id, Departamento departamentoAtualizado) {
        Departamento departamentoExistente = buscarPorId(id);
        
        // Atualiza os campos permitidos
        departamentoExistente.setNome(departamentoAtualizado.getNome());
        departamentoExistente.setSigla(departamentoAtualizado.getSigla());
        
        return departamentoRepository.save(departamentoExistente);
    }

    public void deletar(Long id) {
        Departamento departamento = buscarPorId(id);
        departamentoRepository.delete(departamento);
    }
}
