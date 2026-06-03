package com.generation.cadastrorh.model;

public enum Cargo {
    ASSISTENTE(0.0), // Sem bônus
    ANALISTAN1(0.10), 
    ANALISTAN2(0.12),
    ANALISTAN3(0.15),
    SUPERVISOR(0.20),
    GERENTE(0.25);  // 25% de bônus

    private final double percentualBonus;

    Cargo(double percentualBonus) {
        this.percentualBonus = percentualBonus;
    }

    public double getPercentualBonus() {
        return percentualBonus;
    }
}