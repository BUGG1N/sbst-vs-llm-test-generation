# Comparação SBST (EvoSuite) vs LLM (Copilot/ChatGPT)

**Disciplina:** Inteligência Artificial na Engenharia de Software  
**Autor:** Guilherme V. M. Amadeu  
**Data:** Janeiro 2026

## Objetivo

Comparar a eficácia e a cobertura de testes gerados por algoritmos evolutivos (SBST) versus IA generativa (LLM).

## Estrutura do Repositório

```
atividade-sbst-llm/
├── src/main/java/com/atividade/
│   └── Calculator.java              # Classe alvo (20 métodos, 358 linhas)
├── evosuite/com/atividade/
│   ├── Calculator_ESTest.java       # Suíte EvoSuite (71 testes)
│   └── Calculator_ESTest_scaffolding.java
├── llm/com/atividade/
│   └── CalculatorLLMTest.java       # Suíte LLM (96 testes)
├── relatorio/
│   ├── RELATORIO_FINAL.md           # Relatório completo
│   ├── metricas.md                  # Métricas detalhadas
│   └── metricas_multi_run.md        # Resultados de execução
├── tools/
│   └── evosuite-1.0.6.jar           # JAR do EvoSuite
├── pom.xml                          # Configuração Maven (JaCoCo, PITest)
├── run_multirun.ps1                 # Script de coleta de métricas
└── README.md                        # Este arquivo
```

## Resultados Principais

| Métrica | EvoSuite (SBST) | LLM (Copilot) |
|---------|-----------------|---------------|
| Número de Testes | 71 | 96 |
| Line Coverage | 100% | 100% |
| Branch Coverage | 100% | 100% |
| Mutation Score | **91%** | **93%** |
| Mutantes Mortos | 110/121 | 113/121 |

**Conclusão:** A suíte LLM apresentou mutation score 2% superior, com maior eficácia na detecção de mutantes do tipo `ConditionalsBoundary`.

## Pré-requisitos

- Java JDK 11+ (testado com Eclipse Adoptium 21.0.8)
- Maven 3.x (incluso via Maven Wrapper)

## Como Executar

### 1. Compilar o projeto

```powershell
.\mvnw.cmd compile
```

### 2. Testar suíte EvoSuite

```powershell
# Copiar suíte
Copy-Item "evosuite\com\atividade\Calculator_ESTest.java" -Destination "src\test\java\com\atividade\"

# Executar testes + JaCoCo
.\mvnw.cmd clean test

# Executar PITest
.\mvnw.cmd org.pitest:pitest-maven:mutationCoverage
```

### 3. Testar suíte LLM

```powershell
# Limpar e copiar suíte
Remove-Item "src\test\java\com\atividade\*.java" -Force -ErrorAction SilentlyContinue
Copy-Item "llm\com\atividade\CalculatorLLMTest.java" -Destination "src\test\java\com\atividade\"

# Executar testes + JaCoCo
.\mvnw.cmd clean test

# Executar PITest
.\mvnw.cmd org.pitest:pitest-maven:mutationCoverage
```

### 4. Script automatizado

```powershell
.\run_multirun.ps1
```

## Relatórios Gerados

- **JaCoCo (Cobertura):** `target/site/jacoco/index.html`
- **PITest (Mutação):** `target/pit-reports/[timestamp]/index.html`

## Ferramentas Utilizadas

| Ferramenta | Versão | Função |
|------------|--------|--------|
| EvoSuite | 1.0.6 | Geração SBST (branch coverage) |
| GitHub Copilot | Claude Opus 4.5 | Geração assistida LLM |
| JaCoCo | 0.8.12 | Cobertura de código |
| PITest | 1.17.1 | Teste de mutação |
| JUnit | 4.13.2 | Framework de testes |

## Documentação

O relatório completo está em [relatorio/RELATORIO_FINAL.md](relatorio/RELATORIO_FINAL.md), incluindo:

- Metodologia detalhada
- Análise por mutador
- Ameaças à validade
- Prompt LLM para reprodutibilidade
- Plano experimental multi-run

## Licença

Projeto acadêmico - uso educacional.
