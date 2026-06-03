package com.generation.cadastrorh.model;

import java.math.BigDecimal;
import java.time.LocalDate;

    public class FolhaPagamento {
        private String nomeFuncionario;
        private String cpf;
        private Cargo cargo;
        private LocalDate dataEmissao;
        
        // Proventos (Créditos)
        private BigDecimal salarioBase;
        private BigDecimal valorBonusCargo;
        private BigDecimal valorAdicionalFerias; // O 1/3 de férias
        private BigDecimal salarioBrutoTotal;     // Salário Base + Bônus + Férias
        
        // Descontos
        private BigDecimal descontoInss;
        private BigDecimal descontoIrrf;
        private BigDecimal descontoValeTransporte;
        
        // Final
        private BigDecimal salarioLiquido;
    
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public BigDecimal getDescontoInss() {
		return descontoInss;
	}
	public void setDescontoInss(BigDecimal descontoInss) {
		this.descontoInss = descontoInss;
	}
	public BigDecimal getDescontoIrrf() {
		return descontoIrrf;
	}
	public void setDescontoIrrf(BigDecimal descontoIrrf) {
		this.descontoIrrf = descontoIrrf;
	}
	public BigDecimal getSalarioLiquido() {
		return salarioLiquido;
	}
	public void setSalarioLiquido(BigDecimal salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public BigDecimal getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(BigDecimal salarioBase) {
		this.salarioBase = salarioBase;
	}
	public BigDecimal getValorBonusCargo() {
		return valorBonusCargo;
	}
	public void setValorBonusCargo(BigDecimal valorBonusCargo) {
		this.valorBonusCargo = valorBonusCargo;
	}
	public BigDecimal getValorAdicionalFerias() {
		return valorAdicionalFerias;
	}
	public void setValorAdicionalFerias(BigDecimal valorAdicionalFerias) {
		this.valorAdicionalFerias = valorAdicionalFerias;
	}
	public BigDecimal getSalarioBrutoTotal() {
		return salarioBrutoTotal;
	}
	public void setSalarioBrutoTotal(BigDecimal salarioBrutoTotal) {
		this.salarioBrutoTotal = salarioBrutoTotal;
	}
	public BigDecimal getDescontoValeTransporte() {
		return descontoValeTransporte;
	}
	public void setDescontoValeTransporte(BigDecimal descontoValeTransporte) {
		this.descontoValeTransporte = descontoValeTransporte;
	}
    
    
}
