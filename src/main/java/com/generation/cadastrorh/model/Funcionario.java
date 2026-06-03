package com.generation.cadastrorh.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    private BigDecimal salario;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
    
	@Enumerated(EnumType.STRING)
	private Cargo cargo;

	private boolean optaValeTransporte;

	private BigDecimal valorGastoTransporteMensal; // Custo real do VT que a empresa gastaria

	private boolean estaDeFerias;



	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public boolean isOptaValeTransporte() {
		return optaValeTransporte;
	}

	public void setOptaValeTransporte(boolean optaValeTransporte) {
		this.optaValeTransporte = optaValeTransporte;
	}

	public BigDecimal getValorGastoTransporteMensal() {
		return valorGastoTransporteMensal;
	}

	public void setValorGastoTransporteMensal(BigDecimal valorGastoTransporteMensal) {
		this.valorGastoTransporteMensal = valorGastoTransporteMensal;
	}

	public boolean isEstaDeFerias() {
		return estaDeFerias;
	}

	public void setEstaDeFerias(boolean estaDeFerias) {
		this.estaDeFerias = estaDeFerias;
	}
	
	
	
}