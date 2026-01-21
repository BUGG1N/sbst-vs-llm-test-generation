# ============================================================
# SCRIPT DE EXECUÇÃO MULTI-RUN - SBST vs LLM
# Projeto: Comparação EvoSuite vs Copilot/ChatGPT
# Autor: Script gerado automaticamente
# Data: Janeiro 2026
# ============================================================

$ErrorActionPreference = "Stop"
$PROJECT_ROOT = $PSScriptRoot
if (-not $PROJECT_ROOT) { $PROJECT_ROOT = Get-Location }

$RESULTS_FILE = "$PROJECT_ROOT\relatorio\metricas_multi_run.md"
$TEST_SRC = "$PROJECT_ROOT\src\test\java\com\atividade"

Write-Host ""
Write-Host "============================================" -ForegroundColor Cyan
Write-Host "   SBST vs LLM - Coleta de Métricas" -ForegroundColor Cyan
Write-Host "============================================" -ForegroundColor Cyan
Write-Host ""

# Criar diretório de teste se não existir
if (-not (Test-Path $TEST_SRC)) {
    New-Item -ItemType Directory -Path $TEST_SRC -Force | Out-Null
}

# Criar arquivo de resultados
$header = @"
# Métricas Multi-Run - SBST vs LLM

**Data de Execução:** $(Get-Date -Format "yyyy-MM-dd HH:mm:ss")

## Informações do Ambiente

| Item | Valor |
|------|-------|
| Sistema Operacional | $([System.Environment]::OSVersion.VersionString) |
| CPU | $((Get-WmiObject Win32_Processor).Name) |
| RAM | $([math]::Round((Get-WmiObject Win32_ComputerSystem).TotalPhysicalMemory / 1GB, 2)) GB |
| Java | $(java -version 2>&1 | Select-Object -First 1) |

---

## Tabela de Resultados

| Execução | Suite | #Testes | Line% | Branch% | Mutantes | Mortos | Sobrev. | Score% | Tempo(s) |
|----------|-------|---------|-------|---------|----------|--------|---------|--------|----------|
"@

$header | Out-File -FilePath $RESULTS_FILE -Encoding utf8

# Função para limpar diretório de teste
function Clear-TestDir {
    if (Test-Path $TEST_SRC) {
        Get-ChildItem $TEST_SRC -Filter "*.java" | Remove-Item -Force -ErrorAction SilentlyContinue
    }
}

# Função para executar métricas de uma suíte
function Run-Metrics {
    param(
        [string]$SuiteName,
        [string]$TestFile,
        [string]$SuiteType
    )
    
    Write-Host ""
    Write-Host ">>> Executando: $SuiteName" -ForegroundColor Yellow
    Write-Host "    Arquivo: $TestFile" -ForegroundColor Gray
    
    # Limpar e copiar suite
    Clear-TestDir
    Copy-Item $TestFile -Destination $TEST_SRC -Force
    
    # Medir tempo total
    $stopwatch = [System.Diagnostics.Stopwatch]::StartNew()
    
    # Compilar e testar (JaCoCo)
    Write-Host "    [1/3] Compilando projeto..." -ForegroundColor Gray
    $compileOutput = & .\mvnw.cmd clean compile test-compile 2>&1
    
    Write-Host "    [2/3] Executando testes + JaCoCo..." -ForegroundColor Gray
    $testOutput = & .\mvnw.cmd test 2>&1
    
    # Extrair número de testes do output
    $testsLine = $testOutput | Select-String "Tests run:"
    if ($testsLine) {
        Write-Host "    $testsLine" -ForegroundColor Green
    }
    
    # PITest
    Write-Host "    [3/3] Executando PITest (pode demorar ~30s)..." -ForegroundColor Gray
    $pitestOutput = & .\mvnw.cmd org.pitest:pitest-maven:mutationCoverage 2>&1
    
    $stopwatch.Stop()
    $tempoTotal = [math]::Round($stopwatch.Elapsed.TotalSeconds, 1)
    
    # Verificar se PITest gerou relatório
    $pitReportDir = Get-ChildItem "$PROJECT_ROOT\target\pit-reports" -Directory | Sort-Object LastWriteTime -Descending | Select-Object -First 1
    
    Write-Host ""
    Write-Host "    ✓ Execução concluída em $tempoTotal segundos" -ForegroundColor Green
    
    if ($pitReportDir) {
        Write-Host "    → Relatório PITest: $($pitReportDir.FullName)\index.html" -ForegroundColor Cyan
    }
    Write-Host "    → Relatório JaCoCo: $PROJECT_ROOT\target\site\jacoco\index.html" -ForegroundColor Cyan
    
    # Retornar linha para tabela (com placeholders para preenchimento manual)
    return "| $SuiteName | $SuiteType | [VER_CONSOLE] | [VER_JACOCO] | [VER_JACOCO] | [VER_PITEST] | [VER_PITEST] | [VER_PITEST] | [VER_PITEST] | $tempoTotal |"
}

# ============================================================
# MENU DE SELEÇÃO
# ============================================================

Write-Host "Selecione a suíte para testar:" -ForegroundColor White
Write-Host "  1. EvoSuite (SBST)" -ForegroundColor Yellow
Write-Host "  2. LLM (Copilot/ChatGPT)" -ForegroundColor Yellow
Write-Host "  3. Ambas (sequencial)" -ForegroundColor Yellow
Write-Host ""

$choice = Read-Host "Digite sua escolha (1, 2 ou 3)"

$evoFile = "$PROJECT_ROOT\evosuite\com\atividade\Calculator_ESTest.java"
$llmFile = "$PROJECT_ROOT\llm\com\atividade\CalculatorLLMTest.java"

switch ($choice) {
    "1" {
        if (Test-Path $evoFile) {
            $result = Run-Metrics -SuiteName "EVO_S1" -TestFile $evoFile -SuiteType "EvoSuite"
            $result | Out-File -FilePath $RESULTS_FILE -Append -Encoding utf8
        } else {
            Write-Host "ERRO: Arquivo EvoSuite não encontrado: $evoFile" -ForegroundColor Red
        }
    }
    "2" {
        if (Test-Path $llmFile) {
            $result = Run-Metrics -SuiteName "LLM_R1" -TestFile $llmFile -SuiteType "LLM"
            $result | Out-File -FilePath $RESULTS_FILE -Append -Encoding utf8
        } else {
            Write-Host "ERRO: Arquivo LLM não encontrado: $llmFile" -ForegroundColor Red
        }
    }
    "3" {
        if (Test-Path $evoFile) {
            $result = Run-Metrics -SuiteName "EVO_S1" -TestFile $evoFile -SuiteType "EvoSuite"
            $result | Out-File -FilePath $RESULTS_FILE -Append -Encoding utf8
        }
        
        if (Test-Path $llmFile) {
            $result = Run-Metrics -SuiteName "LLM_R1" -TestFile $llmFile -SuiteType "LLM"
            $result | Out-File -FilePath $RESULTS_FILE -Append -Encoding utf8
        }
    }
    default {
        Write-Host "Opção inválida. Saindo." -ForegroundColor Red
        exit 1
    }
}

# ============================================================
# INSTRUÇÕES FINAIS
# ============================================================

$instructions = @"

---

## Como Preencher os Placeholders

### 1. Número de Testes [VER_CONSOLE]
Procure no console Maven a linha:
```
Tests run: X, Failures: 0, Errors: 0, Skipped: 0
```
O valor X é o número de testes.

### 2. Line% e Branch% [VER_JACOCO]
1. Abra: ``target\site\jacoco\index.html``
2. Clique em ``com.atividade``
3. Clique em ``Calculator``
4. Veja as colunas:
   - **Cov.** (linha "Total"): Line Coverage
   - **Branches** (linha "Total"): Branch Coverage

### 3. Métricas PITest [VER_PITEST]
1. Abra: ``target\pit-reports\[timestamp]\index.html``
2. Na tabela principal, procure ``com.atividade.Calculator``
3. Anote:
   - **Number of Classes**: geralmente 1
   - **Line Coverage**: % de linhas
   - **Mutation Coverage**: % mutation score
   - **Mutations**: total de mutantes (ex: 121)
   
Para calcular Mortos e Sobreviventes:
- Mortos = Mutantes × (Mutation Coverage / 100)
- Sobreviventes = Mutantes - Mortos

---

## Estatísticas (preencher manualmente)

### EvoSuite
- Mutation Score: _____%
- Tempo médio: _____s

### LLM
- Mutation Score: _____%
- Tempo médio: _____s

### Diferença
- Δ Mutation Score: _____% (LLM - EvoSuite)

"@

$instructions | Out-File -FilePath $RESULTS_FILE -Append -Encoding utf8

Write-Host ""
Write-Host "============================================" -ForegroundColor Cyan
Write-Host "   EXECUÇÃO CONCLUÍDA!" -ForegroundColor Green
Write-Host "============================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Resultados salvos em:" -ForegroundColor White
Write-Host "  $RESULTS_FILE" -ForegroundColor Yellow
Write-Host ""
Write-Host "Próximos passos:" -ForegroundColor White
Write-Host "  1. Abrir os relatórios HTML indicados acima" -ForegroundColor Gray
Write-Host "  2. Preencher os placeholders no arquivo de métricas" -ForegroundColor Gray
Write-Host "  3. Copiar dados para o RELATORIO_FINAL.md" -ForegroundColor Gray
Write-Host ""
