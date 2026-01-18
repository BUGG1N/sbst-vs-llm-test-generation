package com.atividade;

/**
 * Classe Calculator - Alvo para comparação SBST vs LLM
 * 
 * Contém operações aritméticas com validações, tratamento de exceções
 * e múltiplos ramos para permitir uma comparação significativa entre
 * as abordagens de geração de testes.
 * 
 * FQCN: com.atividade.Calculator
 */
public class Calculator {

    /**
     * Soma dois números inteiros.
     * 
     * @param a primeiro operando
     * @param b segundo operando
     * @return soma de a e b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtrai dois números inteiros.
     * 
     * @param a primeiro operando
     * @param b segundo operando
     * @return diferença entre a e b
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplica dois números inteiros.
     * 
     * @param a primeiro operando
     * @param b segundo operando
     * @return produto de a e b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divide dois números inteiros.
     * 
     * @param a dividendo
     * @param b divisor
     * @return quociente da divisão inteira de a por b
     * @throws ArithmeticException se b for zero
     */
    public int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida");
        }
        return a / b;
    }

    /**
     * Calcula o módulo (resto da divisão) de dois números.
     * 
     * @param a dividendo
     * @param b divisor
     * @return resto da divisão de a por b
     * @throws ArithmeticException se b for zero
     */
    public int modulo(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Módulo por zero não é permitido");
        }
        return a % b;
    }

    /**
     * Calcula a potência de um número (base^expoente).
     * 
     * @param base a base
     * @param exponent o expoente (deve ser >= 0)
     * @return base elevado a exponent
     * @throws IllegalArgumentException se o expoente for negativo
     */
    public long power(int base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Expoente negativo não é suportado");
        }
        
        if (exponent == 0) {
            return 1;
        }
        
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * Calcula o fatorial de um número.
     * 
     * @param n número para calcular o fatorial (0 <= n <= 20)
     * @return fatorial de n
     * @throws IllegalArgumentException se n for negativo ou maior que 20
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fatorial de número negativo não existe");
        }
        if (n > 20) {
            throw new IllegalArgumentException("Fatorial de n > 20 causa overflow em long");
        }
        
        if (n == 0 || n == 1) {
            return 1;
        }
        
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calcula o valor absoluto de um número.
     * 
     * @param n número
     * @return valor absoluto de n
     */
    public int abs(int n) {
        if (n < 0) {
            return -n;
        }
        return n;
    }

    /**
     * Retorna o maior entre dois números.
     * 
     * @param a primeiro número
     * @param b segundo número
     * @return o maior entre a e b
     */
    public int max(int a, int b) {
        if (a >= b) {
            return a;
        }
        return b;
    }

    /**
     * Retorna o menor entre dois números.
     * 
     * @param a primeiro número
     * @param b segundo número
     * @return o menor entre a e b
     */
    public int min(int a, int b) {
        if (a <= b) {
            return a;
        }
        return b;
    }

    /**
     * Verifica se um número é par.
     * 
     * @param n número a verificar
     * @return true se n for par, false caso contrário
     */
    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    /**
     * Verifica se um número é ímpar.
     * 
     * @param n número a verificar
     * @return true se n for ímpar, false caso contrário
     */
    public boolean isOdd(int n) {
        return n % 2 != 0;
    }

    /**
     * Verifica se um número é primo.
     * 
     * @param n número a verificar (deve ser >= 0)
     * @return true se n for primo, false caso contrário
     * @throws IllegalArgumentException se n for negativo
     */
    public boolean isPrime(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Número deve ser não-negativo");
        }
        
        if (n < 2) {
            return false;
        }
        
        if (n == 2) {
            return true;
        }
        
        if (n % 2 == 0) {
            return false;
        }
        
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula o máximo divisor comum (MDC) de dois números usando algoritmo de Euclides.
     * 
     * @param a primeiro número (deve ser >= 0)
     * @param b segundo número (deve ser >= 0)
     * @return MDC de a e b
     * @throws IllegalArgumentException se ambos forem zero ou algum for negativo
     */
    public int gcd(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Números devem ser não-negativos");
        }
        if (a == 0 && b == 0) {
            throw new IllegalArgumentException("MDC de 0 e 0 não é definido");
        }
        
        // Caso especial: um dos números é zero
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        
        // Algoritmo de Euclides
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Calcula o mínimo múltiplo comum (MMC) de dois números.
     * 
     * @param a primeiro número (deve ser > 0)
     * @param b segundo número (deve ser > 0)
     * @return MMC de a e b
     * @throws IllegalArgumentException se algum número for <= 0
     */
    public int lcm(int a, int b) {
        if (a <= 0 || b <= 0) {
            throw new IllegalArgumentException("Números devem ser positivos");
        }
        return (a / gcd(a, b)) * b;
    }

    /**
     * Retorna o sinal de um número.
     * 
     * @param n número
     * @return 1 se positivo, -1 se negativo, 0 se zero
     */
    public int signum(int n) {
        if (n > 0) {
            return 1;
        } else if (n < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Calcula a média aritmética de um array de números.
     * 
     * @param numbers array de números
     * @return média aritmética
     * @throws IllegalArgumentException se o array for null ou vazio
     */
    public double average(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array não pode ser nulo ou vazio");
        }
        
        long sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        return (double) sum / numbers.length;
    }

    /**
     * Classifica um número como "positivo", "negativo" ou "zero".
     * 
     * @param n número a classificar
     * @return string descrevendo a classificação
     */
    public String classify(int n) {
        if (n > 0) {
            return "positivo";
        } else if (n < 0) {
            return "negativo";
        } else {
            return "zero";
        }
    }

    /**
     * Verifica se um número está dentro de um intervalo (inclusivo).
     * 
     * @param value valor a verificar
     * @param min limite inferior
     * @param max limite superior
     * @return true se min <= value <= max
     * @throws IllegalArgumentException se min > max
     */
    public boolean isInRange(int value, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Limite inferior não pode ser maior que o superior");
        }
        return value >= min && value <= max;
    }

    /**
     * Limita um valor a um intervalo (clamp).
     * 
     * @param value valor a limitar
     * @param min limite inferior
     * @param max limite superior
     * @return valor limitado ao intervalo [min, max]
     * @throws IllegalArgumentException se min > max
     */
    public int clamp(int value, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Limite inferior não pode ser maior que o superior");
        }
        
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }
}
