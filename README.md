# Atividade Prática: Comparação SBST vs LLM

Projeto para comparação de geração automática de testes unitários usando:
- **EvoSuite (SBST)**: Search-Based Software Testing
- **LLM (Copilot/ChatGPT)**: Geração assistida por IA

## Estrutura do Projeto

```
atividade-sbst-llm/
├── src/
│   ├── main/java/com/atividade/    # Código-fonte alvo
│   │   └── Calculator.java         # Classe para testar
│   └── test/java/                  # Suíte ativa (copiar de evosuite/ ou llm/)
├── evosuite/                       # Suíte gerada pelo EvoSuite
├── llm/                            # Suíte gerada pelo LLM
│   └── com/atividade/
│       └── CalculatorLLMTest.java
├── tools/                          # JAR do EvoSuite
├── relatorio/                      # Anotações e métricas
│   └── metricas.md
├── pom.xml                         # Configuração Maven
└── RELATORIO.md                    # Relatório final
```

## Pré-requisitos

- Java JDK 11+
- Maven 3.x
- EvoSuite 1.0.6 (baixar para `tools/`)

## Comandos Principais

### 1. Compilar o projeto
```bash
mvn -q -DskipTests package
```

### 2. Baixar EvoSuite
```powershell
# Windows PowerShell
New-Item -ItemType Directory -Force tools | Out-Null
Invoke-WebRequest -Uri "https://github.com/EvoSuite/evosuite/releases/download/v1.0.6/evosuite-1.0.6.jar" -OutFile "tools/evosuite-1.0.6.jar"
```

### 3. Gerar testes com EvoSuite
```bash
java -jar tools/evosuite-1.0.6.jar -class com.atividade.Calculator -projectCP target/classes -criterion branch -seed 1
```

### 4. Ativar suíte EvoSuite
```powershell
# Windows PowerShell
Remove-Item -Recurse -Force src\test\java -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force src\test\java | Out-Null
Copy-Item -Recurse -Force evosuite-tests\* src\test\java\
```

### 5. Ativar suíte LLM
```powershell
# Windows PowerShell
Remove-Item -Recurse -Force src\test\java -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force src\test\java | Out-Null
Copy-Item -Recurse -Force llm\* src\test\java\
```

### 6. Executar testes + cobertura (JaCoCo)
```bash
mvn -q test
```
Relatório: `target/site/jacoco/index.html`

### 7. Executar análise de mutação (PITest)
```bash
mvn -q test-compile org.pitest:pitest-maven:mutationCoverage
```
Relatório: `target/pit-reports/<timestamp>/index.html`

## Classe Alvo

**FQCN:** `com.atividade.Calculator`

Métodos implementados:
- Operações básicas: `add`, `subtract`, `multiply`, `divide`, `modulo`
- Matemáticos: `power`, `factorial`, `abs`, `max`, `min`, `signum`
- Verificações: `isEven`, `isOdd`, `isPrime`, `isInRange`
- Utilitários: `gcd`, `lcm`, `average`, `classify`, `clamp`

## Entregáveis

1. ✅ Repositório Git com as duas suítes (`/evosuite` e `/llm`)
2. ✅ Template de relatório (`RELATORIO.md`)
3. ⏳ Métricas preenchidas (`relatorio/metricas.md`)
4. ⏳ Análise crítica final

## Próximos Passos

1. Compilar o projeto com `mvn -q -DskipTests package`
2. Baixar EvoSuite para `tools/`
3. Gerar testes com EvoSuite e copiar para `evosuite/`
4. Coletar métricas de cada suíte (JaCoCo + PITest)
5. Preencher o relatório final
