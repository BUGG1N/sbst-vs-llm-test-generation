# Métricas Multi-Run - SBST vs LLM

**Data de Execução:** 2026-01-21 16:40:00

## Informações do Ambiente

| Item | Valor |
|------|-------|
| Sistema Operacional | Microsoft Windows NT 10.0.22631.0 |
| Java | openjdk version "21.0.8" 2025-07-15 LTS |
| CPU | [PREENCHER: Ver comando abaixo] |
| RAM | [PREENCHER: Ver comando abaixo] |

**Comando para obter informações do sistema:**
```powershell
Write-Host "CPU: $((Get-WmiObject Win32_Processor).Name)"
Write-Host "RAM: $([math]::Round((Get-WmiObject Win32_ComputerSystem).TotalPhysicalMemory / 1GB, 2)) GB"
```

---

## Tabela de Resultados (Execução Real)

| Execução | Suite | #Testes | Line% | Branch% | Mutantes | Mortos | Sobrev. | Score% | Tempo(s) |
|----------|-------|---------|-------|---------|----------|--------|---------|--------|----------|
| EVO_S1 | EvoSuite | 71 | 100% | 100% | 121 | 110 | 11 | 91% | ~10s |
| LLM_R1 | LLM | 96 | 100% | 100% | 121 | 113 | 8 | 93% | ~13s |

---

## Detalhamento por Mutador

### EvoSuite (SBST)

| Mutador | Gerados | Mortos | Score |
|---------|---------|--------|-------|
| ConditionalsBoundary | 25 | 14 | 56% |
| PrimitiveReturns | 25 | 25 | 100% |
| InvertNegs | 1 | 1 | 100% |
| BooleanTrueReturns | 6 | 6 | 100% |
| MathMutator | 17 | 17 | 100% |
| EmptyObjectReturns | 3 | 3 | 100% |
| BooleanFalseReturns | 2 | 2 | 100% |
| NegateConditionals | 42 | 42 | 100% |
| **TOTAL** | **121** | **110** | **91%** |

### LLM (Copilot)

| Mutador | Gerados | Mortos | Score |
|---------|---------|--------|-------|
| ConditionalsBoundary | 25 | 17 | 68% |
| PrimitiveReturns | 25 | 25 | 100% |
| InvertNegs | 1 | 1 | 100% |
| BooleanTrueReturns | 6 | 6 | 100% |
| MathMutator | 17 | 17 | 100% |
| EmptyObjectReturns | 3 | 3 | 100% |
| BooleanFalseReturns | 2 | 2 | 100% |
| NegateConditionals | 42 | 42 | 100% |
| **TOTAL** | **121** | **113** | **93%** |

---

## Análise Comparativa

### Diferença Principal: ConditionalsBoundary

| Métrica | EvoSuite | LLM | Δ |
|---------|----------|-----|---|
| Mutantes CB mortos | 14/25 (56%) | 17/25 (68%) | +3 (+12%) |
| Mutation Score total | 91% | 93% | +2% |

### Interpretação

1. **ConditionalsBoundary** é o único mutador com diferença significativa
2. A suíte LLM matou **3 mutantes adicionais** neste tipo
3. Ambas as suítes atingiram 100% nos demais mutadores
4. A diferença total é de **2 pontos percentuais** (91% vs 93%)

---

## Estatísticas Descritivas (Run Único)

| Abordagem | Mutation Score | #Testes | Tempo Execução |
|-----------|----------------|---------|----------------|
| EvoSuite (seed=1) | 91% | 71 | ~10s |
| LLM (run=1) | 93% | 96 | ~13s |
| **Δ (LLM - EVO)** | **+2%** | **+25** | **+3s** |

---

## Notas de Reprodutibilidade

### Comandos Executados

**EvoSuite:**
```powershell
# Copiar suíte
Copy-Item "evosuite\com\atividade\Calculator_ESTest.java" -Destination "src\test\java\com\atividade\"

# Executar testes + JaCoCo
.\mvnw.cmd clean test

# Executar PITest
.\mvnw.cmd org.pitest:pitest-maven:mutationCoverage
```

**LLM:**
```powershell
# Copiar suíte
Copy-Item "llm\com\atividade\CalculatorLLMTest.java" -Destination "src\test\java\com\atividade\"

# Executar testes + JaCoCo
.\mvnw.cmd clean test

# Executar PITest
.\mvnw.cmd org.pitest:pitest-maven:mutationCoverage
```

### Localização dos Relatórios

- **JaCoCo HTML:** `target/site/jacoco/index.html`
- **PITest HTML:** `target/pit-reports/[timestamp]/index.html`
- **PITest XML:** `target/pit-reports/[timestamp]/mutations.xml`

---

*Métricas coletadas em 21/01/2026 às 16:40*
