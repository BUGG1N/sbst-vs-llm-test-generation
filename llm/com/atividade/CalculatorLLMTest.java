package com.atividade;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Suíte de testes gerada via LLM (Copilot/ChatGPT) para a classe Calculator.
 * 
 * Padrão: AAA (Arrange/Act/Assert)
 * Framework: JUnit 4.13.2
 * Foco: Cobertura de todos os ramos, casos de borda e exceções
 * 
 * Data de geração: Janeiro 2026
 */
public class CalculatorLLMTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    // ==================== TESTES PARA add() ====================

    @Test
    public void testAdd_PositiveNumbers() {
        // Arrange
        int a = 5;
        int b = 3;
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        assertEquals(8, result);
    }

    @Test
    public void testAdd_NegativeNumbers() {
        // Arrange
        int a = -5;
        int b = -3;
        
        // Act
        int result = calculator.add(a, b);
        
        // Assert
        assertEquals(-8, result);
    }

    @Test
    public void testAdd_MixedSigns() {
        // Arrange & Act & Assert
        assertEquals(2, calculator.add(5, -3));
        assertEquals(-2, calculator.add(-5, 3));
    }

    @Test
    public void testAdd_WithZero() {
        // Act & Assert
        assertEquals(5, calculator.add(5, 0));
        assertEquals(5, calculator.add(0, 5));
        assertEquals(0, calculator.add(0, 0));
    }

    // ==================== TESTES PARA subtract() ====================

    @Test
    public void testSubtract_PositiveResult() {
        assertEquals(2, calculator.subtract(5, 3));
    }

    @Test
    public void testSubtract_NegativeResult() {
        assertEquals(-2, calculator.subtract(3, 5));
    }

    @Test
    public void testSubtract_WithZero() {
        assertEquals(5, calculator.subtract(5, 0));
        assertEquals(-5, calculator.subtract(0, 5));
        assertEquals(0, calculator.subtract(0, 0));
    }

    @Test
    public void testSubtract_SameNumbers() {
        assertEquals(0, calculator.subtract(7, 7));
    }

    // ==================== TESTES PARA multiply() ====================

    @Test
    public void testMultiply_PositiveNumbers() {
        assertEquals(15, calculator.multiply(5, 3));
    }

    @Test
    public void testMultiply_NegativeNumbers() {
        assertEquals(15, calculator.multiply(-5, -3));
    }

    @Test
    public void testMultiply_MixedSigns() {
        assertEquals(-15, calculator.multiply(5, -3));
        assertEquals(-15, calculator.multiply(-5, 3));
    }

    @Test
    public void testMultiply_WithZero() {
        assertEquals(0, calculator.multiply(5, 0));
        assertEquals(0, calculator.multiply(0, 5));
        assertEquals(0, calculator.multiply(0, 0));
    }

    @Test
    public void testMultiply_WithOne() {
        assertEquals(5, calculator.multiply(5, 1));
        assertEquals(5, calculator.multiply(1, 5));
    }

    // ==================== TESTES PARA divide() ====================

    @Test
    public void testDivide_ExactDivision() {
        assertEquals(5, calculator.divide(15, 3));
    }

    @Test
    public void testDivide_IntegerDivision() {
        assertEquals(3, calculator.divide(10, 3)); // trunca para 3
    }

    @Test
    public void testDivide_NegativeNumbers() {
        assertEquals(5, calculator.divide(-15, -3));
    }

    @Test
    public void testDivide_MixedSigns() {
        assertEquals(-5, calculator.divide(15, -3));
        assertEquals(-5, calculator.divide(-15, 3));
    }

    @Test
    public void testDivide_ZeroDividend() {
        assertEquals(0, calculator.divide(0, 5));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivide_ByZero_ThrowsException() {
        calculator.divide(10, 0);
    }

    // ==================== TESTES PARA modulo() ====================

    @Test
    public void testModulo_PositiveNumbers() {
        assertEquals(1, calculator.modulo(10, 3));
    }

    @Test
    public void testModulo_ExactDivision() {
        assertEquals(0, calculator.modulo(9, 3));
    }

    @Test
    public void testModulo_ZeroDividend() {
        assertEquals(0, calculator.modulo(0, 5));
    }

    @Test
    public void testModulo_NegativeDividend() {
        assertEquals(-1, calculator.modulo(-10, 3));
    }

    @Test(expected = ArithmeticException.class)
    public void testModulo_ByZero_ThrowsException() {
        calculator.modulo(10, 0);
    }

    // ==================== TESTES PARA power() ====================

    @Test
    public void testPower_PositiveExponent() {
        assertEquals(8, calculator.power(2, 3));
        assertEquals(27, calculator.power(3, 3));
    }

    @Test
    public void testPower_ExponentZero() {
        assertEquals(1, calculator.power(5, 0));
        assertEquals(1, calculator.power(0, 0)); // Por convenção 0^0 = 1
        assertEquals(1, calculator.power(-5, 0));
    }

    @Test
    public void testPower_ExponentOne() {
        assertEquals(5, calculator.power(5, 1));
        assertEquals(-5, calculator.power(-5, 1));
    }

    @Test
    public void testPower_BaseZero() {
        assertEquals(0, calculator.power(0, 5));
    }

    @Test
    public void testPower_BaseOne() {
        assertEquals(1, calculator.power(1, 100));
    }

    @Test
    public void testPower_NegativeBase() {
        assertEquals(4, calculator.power(-2, 2));  // par = positivo
        assertEquals(-8, calculator.power(-2, 3)); // ímpar = negativo
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPower_NegativeExponent_ThrowsException() {
        calculator.power(2, -1);
    }

    // ==================== TESTES PARA factorial() ====================

    @Test
    public void testFactorial_Zero() {
        assertEquals(1, calculator.factorial(0));
    }

    @Test
    public void testFactorial_One() {
        assertEquals(1, calculator.factorial(1));
    }

    @Test
    public void testFactorial_SmallNumbers() {
        assertEquals(2, calculator.factorial(2));
        assertEquals(6, calculator.factorial(3));
        assertEquals(24, calculator.factorial(4));
        assertEquals(120, calculator.factorial(5));
    }

    @Test
    public void testFactorial_LargeNumber() {
        assertEquals(2432902008176640000L, calculator.factorial(20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorial_NegativeNumber_ThrowsException() {
        calculator.factorial(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorial_OverflowProtection_ThrowsException() {
        calculator.factorial(21);
    }

    // ==================== TESTES PARA abs() ====================

    @Test
    public void testAbs_PositiveNumber() {
        assertEquals(5, calculator.abs(5));
    }

    @Test
    public void testAbs_NegativeNumber() {
        assertEquals(5, calculator.abs(-5));
    }

    @Test
    public void testAbs_Zero() {
        assertEquals(0, calculator.abs(0));
    }

    // ==================== TESTES PARA max() ====================

    @Test
    public void testMax_FirstGreater() {
        assertEquals(10, calculator.max(10, 5));
    }

    @Test
    public void testMax_SecondGreater() {
        assertEquals(10, calculator.max(5, 10));
    }

    @Test
    public void testMax_EqualValues() {
        assertEquals(5, calculator.max(5, 5));
    }

    @Test
    public void testMax_NegativeNumbers() {
        assertEquals(-3, calculator.max(-5, -3));
    }

    // ==================== TESTES PARA min() ====================

    @Test
    public void testMin_FirstSmaller() {
        assertEquals(5, calculator.min(5, 10));
    }

    @Test
    public void testMin_SecondSmaller() {
        assertEquals(5, calculator.min(10, 5));
    }

    @Test
    public void testMin_EqualValues() {
        assertEquals(5, calculator.min(5, 5));
    }

    @Test
    public void testMin_NegativeNumbers() {
        assertEquals(-5, calculator.min(-5, -3));
    }

    // ==================== TESTES PARA isEven() ====================

    @Test
    public void testIsEven_EvenNumbers() {
        assertTrue(calculator.isEven(0));
        assertTrue(calculator.isEven(2));
        assertTrue(calculator.isEven(100));
        assertTrue(calculator.isEven(-4));
    }

    @Test
    public void testIsEven_OddNumbers() {
        assertFalse(calculator.isEven(1));
        assertFalse(calculator.isEven(3));
        assertFalse(calculator.isEven(-5));
    }

    // ==================== TESTES PARA isOdd() ====================

    @Test
    public void testIsOdd_OddNumbers() {
        assertTrue(calculator.isOdd(1));
        assertTrue(calculator.isOdd(3));
        assertTrue(calculator.isOdd(-5));
    }

    @Test
    public void testIsOdd_EvenNumbers() {
        assertFalse(calculator.isOdd(0));
        assertFalse(calculator.isOdd(2));
        assertFalse(calculator.isOdd(-4));
    }

    // ==================== TESTES PARA isPrime() ====================

    @Test
    public void testIsPrime_PrimeNumbers() {
        assertTrue(calculator.isPrime(2));
        assertTrue(calculator.isPrime(3));
        assertTrue(calculator.isPrime(5));
        assertTrue(calculator.isPrime(7));
        assertTrue(calculator.isPrime(11));
        assertTrue(calculator.isPrime(13));
        assertTrue(calculator.isPrime(97));
    }

    @Test
    public void testIsPrime_NotPrime() {
        assertFalse(calculator.isPrime(0));
        assertFalse(calculator.isPrime(1));
        assertFalse(calculator.isPrime(4));
        assertFalse(calculator.isPrime(6));
        assertFalse(calculator.isPrime(9));
        assertFalse(calculator.isPrime(100));
    }

    @Test
    public void testIsPrime_EdgeCase_Two() {
        assertTrue(calculator.isPrime(2)); // único primo par
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsPrime_NegativeNumber_ThrowsException() {
        calculator.isPrime(-5);
    }

    // ==================== TESTES PARA gcd() ====================

    @Test
    public void testGcd_CoprimeNumbers() {
        assertEquals(1, calculator.gcd(7, 11));
    }

    @Test
    public void testGcd_CommonDivisor() {
        assertEquals(6, calculator.gcd(12, 18));
        assertEquals(5, calculator.gcd(15, 25));
    }

    @Test
    public void testGcd_SameNumber() {
        assertEquals(7, calculator.gcd(7, 7));
    }

    @Test
    public void testGcd_OneIsZero() {
        assertEquals(5, calculator.gcd(0, 5));
        assertEquals(5, calculator.gcd(5, 0));
    }

    @Test
    public void testGcd_OneIsOne() {
        assertEquals(1, calculator.gcd(1, 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGcd_BothZero_ThrowsException() {
        calculator.gcd(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGcd_NegativeNumber_ThrowsException() {
        calculator.gcd(-5, 10);
    }

    // ==================== TESTES PARA lcm() ====================

    @Test
    public void testLcm_CoprimeNumbers() {
        assertEquals(77, calculator.lcm(7, 11));
    }

    @Test
    public void testLcm_CommonDivisor() {
        assertEquals(36, calculator.lcm(12, 18));
    }

    @Test
    public void testLcm_SameNumber() {
        assertEquals(7, calculator.lcm(7, 7));
    }

    @Test
    public void testLcm_OneIsOne() {
        assertEquals(100, calculator.lcm(1, 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLcm_ZeroNumber_ThrowsException() {
        calculator.lcm(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLcm_NegativeNumber_ThrowsException() {
        calculator.lcm(-5, 10);
    }

    // ==================== TESTES PARA signum() ====================

    @Test
    public void testSignum_Positive() {
        assertEquals(1, calculator.signum(10));
        assertEquals(1, calculator.signum(1));
    }

    @Test
    public void testSignum_Negative() {
        assertEquals(-1, calculator.signum(-10));
        assertEquals(-1, calculator.signum(-1));
    }

    @Test
    public void testSignum_Zero() {
        assertEquals(0, calculator.signum(0));
    }

    // ==================== TESTES PARA average() ====================

    @Test
    public void testAverage_SingleElement() {
        assertEquals(5.0, calculator.average(new int[]{5}), 0.0001);
    }

    @Test
    public void testAverage_MultipleElements() {
        assertEquals(3.0, calculator.average(new int[]{1, 2, 3, 4, 5}), 0.0001);
    }

    @Test
    public void testAverage_NegativeNumbers() {
        assertEquals(-3.0, calculator.average(new int[]{-1, -2, -3, -4, -5}), 0.0001);
    }

    @Test
    public void testAverage_MixedNumbers() {
        assertEquals(0.0, calculator.average(new int[]{-5, 0, 5}), 0.0001);
    }

    @Test
    public void testAverage_AllZeros() {
        assertEquals(0.0, calculator.average(new int[]{0, 0, 0}), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverage_NullArray_ThrowsException() {
        calculator.average(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverage_EmptyArray_ThrowsException() {
        calculator.average(new int[]{});
    }

    // ==================== TESTES PARA classify() ====================

    @Test
    public void testClassify_Positive() {
        assertEquals("positivo", calculator.classify(10));
        assertEquals("positivo", calculator.classify(1));
    }

    @Test
    public void testClassify_Negative() {
        assertEquals("negativo", calculator.classify(-10));
        assertEquals("negativo", calculator.classify(-1));
    }

    @Test
    public void testClassify_Zero() {
        assertEquals("zero", calculator.classify(0));
    }

    // ==================== TESTES PARA isInRange() ====================

    @Test
    public void testIsInRange_ValueInside() {
        assertTrue(calculator.isInRange(5, 1, 10));
    }

    @Test
    public void testIsInRange_ValueAtMin() {
        assertTrue(calculator.isInRange(1, 1, 10));
    }

    @Test
    public void testIsInRange_ValueAtMax() {
        assertTrue(calculator.isInRange(10, 1, 10));
    }

    @Test
    public void testIsInRange_ValueBelow() {
        assertFalse(calculator.isInRange(0, 1, 10));
    }

    @Test
    public void testIsInRange_ValueAbove() {
        assertFalse(calculator.isInRange(11, 1, 10));
    }

    @Test
    public void testIsInRange_NegativeRange() {
        assertTrue(calculator.isInRange(-5, -10, -1));
        assertFalse(calculator.isInRange(0, -10, -1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsInRange_InvalidRange_ThrowsException() {
        calculator.isInRange(5, 10, 1); // min > max
    }

    // ==================== TESTES PARA clamp() ====================

    @Test
    public void testClamp_ValueInside() {
        assertEquals(5, calculator.clamp(5, 1, 10));
    }

    @Test
    public void testClamp_ValueBelow() {
        assertEquals(1, calculator.clamp(0, 1, 10));
    }

    @Test
    public void testClamp_ValueAbove() {
        assertEquals(10, calculator.clamp(15, 1, 10));
    }

    @Test
    public void testClamp_ValueAtMin() {
        assertEquals(1, calculator.clamp(1, 1, 10));
    }

    @Test
    public void testClamp_ValueAtMax() {
        assertEquals(10, calculator.clamp(10, 1, 10));
    }

    @Test
    public void testClamp_NegativeRange() {
        assertEquals(-5, calculator.clamp(-5, -10, -1));
        assertEquals(-10, calculator.clamp(-15, -10, -1));
        assertEquals(-1, calculator.clamp(0, -10, -1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClamp_InvalidRange_ThrowsException() {
        calculator.clamp(5, 10, 1); // min > max
    }
}
