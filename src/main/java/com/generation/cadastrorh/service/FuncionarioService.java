package com.generation.cadastrorh.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.cadastrorh.model.Departamento;
import com.generation.cadastrorh.model.Funcionario;
import com.generation.cadastrorh.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoService departamentoService;

    public Funcionario salvar(Funcionario funcionario) {
        // Regra 1: Validar CPF único
        if (funcionarioRepository.findByCpf(funcionario.getCpf()).isPresent()) {
            throw new RuntimeException("Já existe um funcionário cadastrado com este CPF!");
        }

        // Regra 2: Validar E-mail único
        if (funcionarioRepository.findByEmail(funcionario.getEmail()).isPresent()) {
            throw new RuntimeException("Já existe um funcionário cadastrado com este E-mail!");
        }

        // Regra 3: Garantir que o departamento existe
        Departamento depto = departamentoService.buscarPorId(funcionario.getDepartamento().getId());
        funcionario.setDepartamento(depto);

        // Se passou por todas as validações, salva no banco
        return funcionarioRepository.save(funcionario);
    }
// lista todos os funcionarios
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }
// busca funcionario por id
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado!"));
    }
}