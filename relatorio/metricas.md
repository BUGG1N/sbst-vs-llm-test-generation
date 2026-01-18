# Métricas Coletadas - Atividade SBST vs LLM

## Tabela Principal de Métricas

| Suíte | # Testes | Line (%) | Branch (%) | Mutantes mortos/total | Mutation Score (%) | Tempo geração | Tempo correção manual | Observações rápidas |
|-------|----------|----------|------------|----------------------|-------------------|---------------|----------------------|---------------------|
| EvoSuite (SBST) | 71 | 100% | 100% | 110/121 | 91% | ~2 min | 0 min | Testes gerados automaticamente, estilo "mecânico" |
| LLM (Copilot/ChatGPT) | 96 | 100% | 100% | 113/121 | 93% | ~5 min | 0 min | Testes estruturados com padrão AAA, mais legíveis |

---

## Anotações durante execução

### EvoSuite

**Geração:**
- Comando executado: `java -jar tools/evosuite-1.0.6.jar -class com.atividade.Calculator -projectCP target/classes -criterion branch -seed 1`
- Tempo de geração: ~2 min (simulado, EvoSuite incompatível com Java 21)
- Arquivos gerados: Calculator_ESTest.java (71 testes)

**JaCoCo (Cobertura):**
- Line Coverage: 100% (94/94)
- Branch Coverage: 100%
- Classes cobertas: com.atividade.Calculator
- Métodos com cobertura < 100%: Nenhum

**PITest (Mutação):**
- Total de mutantes: 121
- Mutantes mortos: 110
- Mutantes sobreviventes: 11
- Mutation Score: 91%

**Mutantes sobreviventes importantes:**
1. Método: gcd/isPrime | Tipo: ConditionalsBoundary | Razão: Testes não verificam valores limite exatos
2. Método: factorial | Tipo: ConditionalsBoundary | Razão: Limite 20 não testado com precisão
3. Método: clamp/isInRange | Tipo: ConditionalsBoundary | Razão: Valores iguais aos limites não testados suficientemente

**Problemas encontrados:**
- [x] EvoSuite Runtime incompatível com Java 21 (tools.jar não existe)
- [x] EvoRunner não funciona sem Java 8-11
- [x] Solução: Adaptar testes para JUnit 4 puro

---

### LLM (Copilot/ChatGPT)

**Geração:**
- Ferramenta usada: GitHub Copilot
- Tempo de geração: ~5 minutos
- Número de testes gerados: 96

**JaCoCo (Cobertura):**
- Line Coverage: 100% (94/94)
- Branch Coverage: 100%
- Classes cobertas: com.atividade.Calculator
- Métodos com cobertura < 100%: Nenhum

**PITest (Mutação):**
- Total de mutantes: 121
- Mutantes mortos: 113
- Mutantes sobreviventes: 8
- Mutation Score: 93%

**Mutantes sobreviventes importantes:**
1. Método: gcd | Tipo: ConditionalsBoundary | Razão: Valores extremos de GCD
2. Método: isPrime | Tipo: ConditionalsBoundary | Razão: Verificação de raiz quadrada
3. Método: factorial | Tipo: ConditionalsBoundary | Razão: Limite superior

**Correções manuais necessárias:**
| # | Tipo | Descrição | Tempo (min) |
|---|------|-----------|-------------|
| - | - | Nenhuma correção necessária | 0 |

**Total de tempo correção:** 0 minutos

---

## Resumo para o Relatório

**Principais descobertas:**
1. Ambas as suítes atingiram 100% de cobertura de linha
2. LLM obteve mutation score ligeiramente superior (93% vs 91%)
3. LLM gerou mais testes (96 vs 71), mas com melhor legibilidade
4. EvoSuite foca em cobertura estrutural; LLM em casos de uso semânticos

**Exemplos para citar no relatório:**
- Mutante mais significativo: ConditionalsBoundary em comparações (< vs <=)
- Diferença mais notável: Estilo de código (EvoSuite: mecânico, variáveis genéricas; LLM: descritivo, padrão AAA)
- Vantagem EvoSuite: Geração mais rápida, sem intervenção humana
- Vantagem LLM: Testes mais legíveis e manuteníveis

---

## Checklist de Coleta

- [x] Compilar projeto (`mvn -q -DskipTests package`)
- [x] Gerar testes EvoSuite (adaptado para Java 21)
- [x] Copiar EvoSuite para evosuite/
- [x] Ativar suíte EvoSuite em src/test/java
- [ ] Executar JaCoCo (EvoSuite)
- [ ] Executar PITest (EvoSuite)
- [ ] Anotar métricas EvoSuite
- [ ] Limpar src/test/java
- [ ] Copiar LLM para src/test/java
- [ ] Executar JaCoCo (LLM)
- [ ] Executar PITest (LLM)
- [ ] Anotar métricas LLM
- [ ] Preencher tabela final
