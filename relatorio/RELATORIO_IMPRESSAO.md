# Comparação SBST (EvoSuite) vs LLM (Copilot/ChatGPT)

**Disciplina:** Inteligência Artificial na Engenharia de Software  
**Autor:** Guilherme V. M. Amadeu - 11322d0014 | **Data:** Janeiro/2026

---

## 1. Introdução

Este estudo compara duas abordagens de geração automática de testes unitários para a classe `com.atividade.Calculator` (20 métodos, 358 linhas):

- **SBST (EvoSuite 1.0.6):** Geração evolutiva otimizando Branch Coverage
- **LLM (GitHub Copilot - Claude Opus 4.5):** Geração assistida com foco em casos de borda

**Ferramentas de medição:** JaCoCo 0.8.12 (cobertura) e PITest 1.17.1 (mutação).

---

## 2. Resultados

### 2.1 Métricas Comparativas

| Métrica | EvoSuite (SBST) | LLM (Copilot) | Δ |
|---------|-----------------|---------------|---|
| **Número de Testes** | 71 | 96 | +25 |
| **Line Coverage** | 100% | 100% | = |
| **Branch Coverage** | 100% | 100% | = |
| **Mutantes Mortos** | 110/121 | 113/121 | +3 |
| **Mutation Score** | **91%** | **93%** | **+2%** |
| **Tempo de Geração** | ~2 min | ~5 min | +3 min |

### 2.2 Análise por Mutador

| Mutador | Gerados | EvoSuite | LLM |
|---------|:-------:|:--------:|:---:|
| ConditionalsBoundary | 25 | 56% | **68%** |
| PrimitiveReturns | 25 | 100% | 100% |
| MathMutator | 17 | 100% | 100% |
| NegateConditionals | 42 | 100% | 100% |
| Outros | 12 | 100% | 100% |

**Observação:** A diferença concentra-se no mutador `ConditionalsBoundary` (+3 mutantes mortos pela LLM).

### 2.3 Mutantes Sobreviventes

Ambas as suítes falharam em matar mutantes do tipo `ConditionalsBoundary` (ex: `<=` → `<`):
- **EvoSuite:** 11 sobreviventes em `isPrime()`, `power()`, `clamp()`
- **LLM:** 8 sobreviventes (testes mais específicos para valores limite)

---

## 3. Qualidade do Código Gerado

| Aspecto | EvoSuite | LLM |
|---------|:--------:|:---:|
| Legibilidade | ★★☆☆☆ | ★★★★★ |
| Nomes descritivos | ✗ (`test00`) | ✓ (`divide_porZero_...`) |
| Padrão AAA | ✗ | ✓ |
| Manutenibilidade | Difícil | Fácil |

**Exemplo EvoSuite:**
```java
@Test public void test40() {
    Calculator calculator0 = new Calculator();
    boolean boolean0 = calculator0.isPrime(0);
    assertFalse(boolean0);
}
```

**Exemplo LLM:**
```java
@Test(expected = ArithmeticException.class)
public void divide_porZero_deveLancarArithmeticException() {
    Calculator calc = new Calculator();
    calc.divide(10, 0);  // Act & Assert
}
```

---

## 4. Discussão

### Cobertura ≠ Eficácia
Ambas atingiram 100% de cobertura, mas a LLM obteve +2% em mutation score, demonstrando que **cobertura não garante qualidade de asserções**.

### Trade-offs

| Critério | Vantagem |
|----------|----------|
| Velocidade de geração | EvoSuite |
| Eficácia (mutation score) | LLM |
| Legibilidade/Manutenção | LLM |
| Automatização total | EvoSuite |

---

## 5. Ameaças à Validade

| Tipo | Principal Ameaça | Mitigação |
|------|------------------|-----------|
| **Interna** | Seed único, LLM não-determinístico | Múltiplos seeds/gerações |
| **Construção** | Mutation score como proxy | Complementar com bugs reais |
| **Externa** | Classe única, domínio aritmético | Diversificar SUTs |

---

## 6. Conclusão

| Cenário | Recomendação |
|---------|--------------|
| Código legado sem testes | **EvoSuite** (rápido) |
| Novo desenvolvimento | **LLM** (manutenível) |
| Testes de regressão | **LLM** (estáveis) |
| Fuzzing/exploração | **EvoSuite** |

**Conclusão:** A abordagem LLM oferece melhor equilíbrio entre eficácia (93% vs 91%) e manutenibilidade. O SBST permanece valioso para geração inicial em massa.

---

## Referências

- Repositório: `evosuite/` (71 testes) e `llm/` (96 testes)
- Relatório completo: `relatorio/RELATORIO_FINAL.md`
- Ambiente: Windows 11, Java 21.0.8, Intel i7-10750H, 24GB RAM
