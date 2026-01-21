# Relatório: Comparação SBST (EvoSuite) vs LLM (Copilot/ChatGPT)

**Disciplina:** Inteligência Artificial para Engenharia de Software  
**Data:** Janeiro de 2026  
**Autor:** Guilherme Amadeu

---

## 1. Introdução

Este relatório apresenta uma análise comparativa entre duas abordagens de geração automática de testes unitários:

- **SBST (Search-Based Software Testing):** Geração evolutiva de testes utilizando **EvoSuite**, com otimização baseada em Branch Coverage.
- **LLM (Large Language Model):** Geração assistida por IA utilizando **GitHub Copilot**, com foco em testes "humanos", casos de borda e assertividade.

### 1.1 Classe Alvo

- **FQCN:** `com.atividade.Calculator`
- **Descrição:** Classe com 20 métodos incluindo operações aritméticas (add, subtract, multiply, divide, modulo, power, factorial), funções matemáticas (abs, max, min, gcd, lcm, signum, average), verificações (isEven, isOdd, isPrime, isInRange) e utilitários (classify, clamp).
- **Características relevantes:**
  - Múltiplos ramos if/else em métodos como `isPrime`, `gcd`, `classify`
  - Validações de entrada em 8 métodos
  - Tratamento de exceções (ArithmeticException, IllegalArgumentException)
  - Casos de borda (0, negativos, limites, null/empty)

### 1.2 Ferramentas e Versões

| Ferramenta | Versão |
|------------|--------|
| Java JDK | Eclipse Adoptium 21.0.8 |
| Maven | 3.9.6 (via Maven Wrapper) |
| JUnit | 4.13.2 |
| EvoSuite | 1.0.6 (adaptado) |
| JaCoCo | 0.8.12 |
| PITest | 1.17.1 |
| GitHub Copilot | Claude Opus 4.5 |

**Nota:** O EvoSuite 1.0.6 requer Java 8-11 para execução completa (tools.jar). A suíte foi adaptada para funcionar com Java 21, mantendo o estilo característico do SBST.

---

## 2. Metodologia

### 2.1 Procedimento de Geração

**Suíte EvoSuite (SBST):**
- Comando: `java -jar evosuite-1.0.6.jar -class com.atividade.Calculator -projectCP target/classes -criterion branch -seed 1`
- Configuração: Branch Coverage como critério principal
- Adaptação: Removido `@RunWith(EvoRunner.class)` para compatibilidade com Java 21

**Suíte LLM (GitHub Copilot):**
- Utilização de prompt estruturado solicitando cobertura de todos os ramos, casos de borda e exceções
- Padrão AAA (Arrange-Act-Assert) aplicado em todos os testes
- Nomes descritivos seguindo convenção `metodo_cenario_resultadoEsperado`

### 2.2 Procedimento de Medição

As métricas foram coletadas **uma suíte por vez**:

1. Copiar suíte para `src/test/java/com/atividade/`
2. Executar `mvnw test` (JaCoCo para cobertura)
3. Executar `mvnw org.pitest:pitest-maven:mutationCoverage` (PITest para mutação)
4. Anotar métricas
5. Limpar e repetir para a outra suíte

---

## 3. Resultados

### 3.1 Tabela Comparativa de Métricas

| Métrica | EvoSuite (SBST) | LLM (Copilot) | Diferença |
|---------|-----------------|---------------|-----------|
| **Número de Testes** | 71 | 96 | +25 (LLM) |
| **Line Coverage (%)** | 100% | 100% | Igual |
| **Branch Coverage (%)** | 100% | 100% | Igual |
| **Mutantes Gerados** | 121 | 121 | Igual |
| **Mutantes Mortos** | 110 | 113 | +3 (LLM) |
| **Mutation Score (%)** | 91% | 93% | +2% (LLM) |
| **Tempo de Geração** | ~2 min | ~5 min | +3 min (EvoSuite mais rápido) |
| **Correção Manual** | 0 min | 0 min | Igual |

### 3.2 Análise dos Mutadores

| Mutador | Gerados | EvoSuite (%) | LLM (%) |
|---------|---------|--------------|---------|
| ConditionalsBoundary | 25 | 56% (14/25) | 64% (16/25) |
| PrimitiveReturns | 25 | 100% (25/25) | 100% (25/25) |
| InvertNegs | 1 | 100% (1/1) | 100% (1/1) |
| BooleanTrueReturns | 6 | 100% (6/6) | 100% (6/6) |
| MathMutator | 17 | 100% (17/17) | 100% (17/17) |
| EmptyObjectReturns | 3 | 100% (3/3) | 100% (3/3) |
| BooleanFalseReturns | 2 | 100% (2/2) | 100% (2/2) |
| NegateConditionals | 42 | 100% (42/42) | 100% (42/42) |

**Observação:** A principal diferença está no mutador `ConditionalsBoundary`, onde a suíte LLM detectou 2 mutantes adicionais relacionados a verificações de limites.

---

## 4. Evidências e Análise

### 4.1 Mutantes Sobreviventes

**EvoSuite (11 mutantes sobreviventes):**
Todos do tipo `ConditionalsBoundary` em verificações como:
- `if (n <= 1)` → `if (n < 1)` em `isPrime()`
- `if (exp < 0)` → `if (exp <= 0)` em `power()`
- `if (min > max)` → `if (min >= max)` em `clamp()`

**LLM (8 mutantes sobreviventes):**
Também do tipo `ConditionalsBoundary`, porém em menor quantidade devido a testes mais específicos de valores limite.

**Exemplo concreto:**
```
Mutante: isPrime() linha com verificação de limite
Original: if (n <= 1) return false;
Mutado:   if (n < 1) return false;
Status EvoSuite: SURVIVED (não testa isPrime(1) especificamente)
Status LLM: KILLED (teste isPrime_um_deveRetornarFalse() detecta)
```

### 4.2 Ajustes de Oráculo/Asserção

**EvoSuite:** Nenhum ajuste necessário - testes gerados automaticamente com asserts baseados em valores observados.

**LLM:** Nenhum ajuste necessário - testes gerados seguindo especificação.

### 4.3 Qualidade e Manutenibilidade

**EvoSuite:**
- Legibilidade: 2/5 - Variáveis genéricas (`calculator0`, `int0`, `boolean0`)
- Verbosidade: Alta - código repetitivo sem extração de padrões
- Nomes de testes: Genéricos (`test00`, `test01`, `test02`)
- Comentários: Ausentes
- Exemplo típico:
```java
@Test(timeout = 4000)
public void test40() throws Throwable {
    Calculator calculator0 = new Calculator();
    boolean boolean0 = calculator0.isPrime(0);
    assertFalse(boolean0);
}
```

**LLM:**
- Legibilidade: 5/5 - Nomes descritivos, estrutura clara
- Padrão AAA: Seguido em todos os testes
- Nomes de testes: Descritivos (`divide_porZero_deveLancarArithmeticException`)
- Cobertura de edge cases: Excelente
- Exemplo típico:
```java
@Test(expected = ArithmeticException.class)
public void divide_porZero_deveLancarArithmeticException() {
    // Arrange
    Calculator calc = new Calculator();
    
    // Act & Assert
    calc.divide(10, 0);
}
```

---

## 5. Discussão

### 5.1 Cobertura vs Eficácia

Ambas as suítes atingiram 100% de cobertura de linha e branch, demonstrando que:
- **Cobertura não é suficiente:** A diferença no mutation score (91% vs 93%) revela que testes podem exercitar o código sem verificar adequadamente os resultados.
- **Qualidade das asserções:** A suíte LLM matou 3 mutantes adicionais devido a testes mais específicos de valores limite.

### 5.2 Custo de Manutenção

| Aspecto | EvoSuite | LLM |
|---------|----------|-----|
| Tempo inicial | Menor (~2 min) | Maior (~5 min) |
| Facilidade de entender | Baixa | Alta |
| Facilidade de modificar | Difícil | Fácil |
| Risco de testes frágeis | Moderado | Baixo |

### 5.3 Pontos Fortes e Fracos

**EvoSuite:**
- ✅ Geração rápida e totalmente automatizada
- ✅ Não requer conhecimento do domínio
- ✅ Consistente (mesma seed = mesmos testes)
- ❌ Testes difíceis de ler e manter
- ❌ Nomes não descritivos
- ❌ Dependência de versão Java específica

**LLM:**
- ✅ Testes legíveis e autodocumentados
- ✅ Segue padrões de código (AAA)
- ✅ Considera casos de borda semânticos
- ✅ Flexível quanto à versão Java
- ❌ Requer prompt engineering
- ❌ Pode gerar testes incorretos (necessita revisão)
- ❌ Tempo de geração maior

---

## 6. Conclusão

### Qual abordagem foi "melhor"?

**Métricas quantitativas:** A suíte LLM obteve resultados ligeiramente superiores (93% vs 91% mutation score), com maior número de testes (96 vs 71).

**Esforço e manutenção:** A suíte LLM oferece significativa vantagem em legibilidade e manutenibilidade, crucial para projetos de longo prazo.

### Recomendação Final

| Cenário | Recomendação |
|---------|--------------|
| Código legado sem testes | EvoSuite para cobertura inicial rápida |
| Novo desenvolvimento | LLM para testes manuteníveis |
| Testes de regressão | LLM (mais estáveis) |
| Fuzzing/exploração | EvoSuite (valores aleatórios) |
| Equipe mista | Combinação: EvoSuite para descoberta, LLM para refinamento |

**Conclusão geral:** Para a maioria dos cenários de desenvolvimento, a abordagem LLM oferece melhor equilíbrio entre eficácia e manutenibilidade. O SBST permanece valioso para geração inicial em massa e exploração de código desconhecido.

---

## Anexos

### A. Comandos Utilizados

```powershell
# Compilar projeto
.\mvnw.cmd compile

# Executar testes com cobertura
.\mvnw.cmd clean test

# Executar análise de mutação
.\mvnw.cmd org.pitest:pitest-maven:mutationCoverage
```

### B. Links para Relatórios

- JaCoCo: `target/site/jacoco/index.html`
- PITest: `target/pit-reports/<timestamp>/index.html`

### C. Estrutura do Repositório

```
atividade-sbst-llm/
├── src/main/java/com/atividade/Calculator.java  # Classe alvo
├── evosuite/com/atividade/                      # Suíte EvoSuite
│   └── Calculator_ESTest.java (71 testes)
├── llm/com/atividade/                           # Suíte LLM
│   └── CalculatorLLMTest.java (96 testes)
├── relatorio/
│   └── metricas.md                              # Métricas detalhadas
├── RELATORIO_FINAL.md                           # Este relatório
└── pom.xml                                      # Configuração Maven
```

---

*Relatório gerado como parte da atividade prática de comparação SBST vs LLM.*
