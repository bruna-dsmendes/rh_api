package com.generation.cadastrorh.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.cadastrorh.model.FolhaPagamento;
import com.generation.cadastrorh.model.Funcionario;

@Service
public class FolhaPagamentoService {

    @Autowired
    private FuncionarioService funcionarioService;

    public FolhaPagamento gerarFolha(Long funcionarioId) {
        Funcionario funcionario = funcionarioService.buscarPorId(funcionarioId);
        BigDecimal salarioBase = funcionario.getSalario();

        // Calcular Bônus por Cargo
        double percentualBonus = funcionario.getCargo() != null ? funcionario.getCargo().getPercentualBonus() : 0.0;
        BigDecimal bonus = salarioBase.multiply(BigDecimal.valueOf(percentualBonus)).setScale(2, RoundingMode.HALF_UP);

        // Calcular Adicional de Férias (1/3)
        BigDecimal adicionalFerias = BigDecimal.ZERO;
        if (funcionario.isEstaDeFerias()) {
            adicionalFerias = salarioBase.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
        }

        // Salário Bruto Total (Base para INSS e IRRF)
        BigDecimal salarioBrutoTotal = salarioBase.add(bonus).add(adicionalFerias);

        // Calculo de Vale Transporte (6% do salário base ou o valor real gasto, o que for menor)
        BigDecimal descontoVT = BigDecimal.ZERO;
        if (funcionario.isOptaValeTransporte()) {
            BigDecimal limiteDescontoVT = salarioBase.multiply(BigDecimal.valueOf(00.06)); // 6%
            BigDecimal custoRealVT = funcionario.getValorGastoTransporteMensal() != null ? 
                    funcionario.getValorGastoTransporteMensal() : BigDecimal.ZERO;
            
            // Regra da CLT: desconta o menor valor entre os 6% e o custo real
            descontoVT = limiteDescontoVT.compareTo(custoRealVT) < 0 ? limiteDescontoVT : custoRealVT;
            descontoVT = descontoVT.setScale(2, RoundingMode.HALF_UP);
        }

        // Calcular INSS (baseado no Bruto Total)
        BigDecimal inss = calcularInss(salarioBrutoTotal);

        // Calcular IRRF (Bruto Total - INSS)
        BigDecimal baseIrrf = salarioBrutoTotal.subtract(inss);
        BigDecimal irrf = calcularIrrf(baseIrrf);

        // Calcular Salário Líquido Final
        BigDecimal totalDescontos = inss.add(irrf).add(descontoVT);
        BigDecimal salarioLiquido = salarioBrutoTotal.subtract(totalDescontos);

        // Montar Objeto da Folha
        FolhaPagamento folha = new FolhaPagamento();
        folha.setNomeFuncionario(funcionario.getNome());
        folha.setCpf(funcionario.getCpf());
        folha.setCargo(funcionario.getCargo());
        folha.setDataEmissao(LocalDate.now());
        folha.setSalarioBase(salarioBase);
        folha.setValorBonusCargo(bonus);
        folha.setValorAdicionalFerias(adicionalFerias);
        folha.setSalarioBrutoTotal(salarioBrutoTotal);
        folha.setDescontoInss(inss);
        folha.setDescontoIrrf(irrf);
        folha.setDescontoValeTransporte(descontoVT);
        folha.setSalarioLiquido(salarioLiquido);

        return folha;
    }

    private BigDecimal calcularInss(BigDecimal salario) {
        double sal = salario.doubleValue();
        double aliquota = (sal <= 2500.0) ? 0.09 : (sal <= 5000.0) ? 0.12 : 0.14;
        return salario.multiply(BigDecimal.valueOf(aliquota)).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calcularIrrf(BigDecimal baseIrrf) {
        double base = baseIrrf.doubleValue();
        double aliquota = 0.0;
        double deducao = 0.0;

        if (base <= 2259.20) return BigDecimal.ZERO;
        
        if (base <= 2826.65) {
            aliquota = 0.075;
            deducao = 169.44;
        } else {
            aliquota = 0.15;
            deducao = 381.44;
        }

        BigDecimal impostoBruto = baseIrrf.multiply(BigDecimal.valueOf(aliquota));
        BigDecimal impostoLiquido = impostoBruto.subtract(BigDecimal.valueOf(deducao));
        return impostoLiquido.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : impostoLiquido.setScale(2, RoundingMode.HALF_UP);
    }
}