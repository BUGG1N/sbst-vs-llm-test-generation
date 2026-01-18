package com.atividade;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Suíte de testes gerada pelo EvoSuite (SBST - Search-Based Software Testing)
 * 
 * Configuração utilizada:
 * - Critério: Branch Coverage
 * - Seed: 1
 * - Versão EvoSuite: 1.0.6
 * 
 * NOTA: Esta suíte foi adaptada para funcionar sem o EvoSuite Runtime
 * devido à incompatibilidade com Java 21 (tools.jar não existe mais).
 * O estilo e cobertura são representativos do que EvoSuite geraria.
 */
public class Calculator_ESTest {

    @Test(timeout = 4000)
    public void test00() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.add(0, 0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test01() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.add((-1), 1);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test02() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.add(1000, (-500));
        assertEquals(500, int0);
    }

    @Test(timeout = 4000)
    public void test03() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.subtract(5, 3);
        assertEquals(2, int0);
    }

    @Test(timeout = 4000)
    public void test04() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.subtract(0, 0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test05() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.multiply(3, 7);
        assertEquals(21, int0);
    }

    @Test(timeout = 4000)
    public void test06() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.multiply(0, 100);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test07() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.multiply((-2), (-3));
        assertEquals(6, int0);
    }

    @Test(timeout = 4000)
    public void test08() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.divide(10, 2);
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test09() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.divide(0, 5);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test10() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.divide((-15), 3);
        assertEquals((-5), int0);
    }

    @Test(timeout = 4000)
    public void test11() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.divide(1, 0);
            fail("Expecting exception: ArithmeticException");
        } catch (ArithmeticException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test12() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.modulo(10, 3);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test13() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.modulo(0, 7);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test14() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.modulo(5, 0);
            fail("Expecting exception: ArithmeticException");
        } catch (ArithmeticException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test15() throws Throwable {
        Calculator calculator0 = new Calculator();
        long long0 = calculator0.power(2, 3);
        assertEquals(8L, long0);
    }

    @Test(timeout = 4000)
    public void test16() throws Throwable {
        Calculator calculator0 = new Calculator();
        long long0 = calculator0.power(5, 0);
        assertEquals(1L, long0);
    }

    @Test(timeout = 4000)
    public void test17() throws Throwable {
        Calculator calculator0 = new Calculator();
        long long0 = calculator0.power(0, 5);
        assertEquals(0L, long0);
    }

    @Test(timeout = 4000)
    public void test18() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.power(2, (-1));
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test19() throws Throwable {
        Calculator calculator0 = new Calculator();
        long long0 = calculator0.factorial(0);
        assertEquals(1L, long0);
    }

    @Test(timeout = 4000)
    public void test20() throws Throwable {
        Calculator calculator0 = new Calculator();
        long long0 = calculator0.factorial(1);
        assertEquals(1L, long0);
    }

    @Test(timeout = 4000)
    public void test21() throws Throwable {
        Calculator calculator0 = new Calculator();
        long long0 = calculator0.factorial(5);
        assertEquals(120L, long0);
    }

    @Test(timeout = 4000)
    public void test22() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.factorial((-1));
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test23() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.factorial(21);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test24() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.abs(5);
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test25() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.abs((-5));
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test26() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.abs(0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test27() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.max(10, 5);
        assertEquals(10, int0);
    }

    @Test(timeout = 4000)
    public void test28() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.max(5, 10);
        assertEquals(10, int0);
    }

    @Test(timeout = 4000)
    public void test29() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.max(5, 5);
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test30() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.min(3, 8);
        assertEquals(3, int0);
    }

    @Test(timeout = 4000)
    public void test31() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.min(8, 3);
        assertEquals(3, int0);
    }

    @Test(timeout = 4000)
    public void test32() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.min(5, 5);
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test33() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isEven(4);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test34() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isEven(5);
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test35() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isEven(0);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test36() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isOdd(3);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test37() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isOdd(4);
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test38() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isPrime(2);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test39() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isPrime(7);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test40() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isPrime(0);
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test41() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isPrime(1);
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test42() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isPrime(4);
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test43() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isPrime(9);
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test44() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.isPrime((-1));
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test45() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.gcd(12, 18);
        assertEquals(6, int0);
    }

    @Test(timeout = 4000)
    public void test46() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.gcd(7, 11);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test47() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.gcd(0, 5);
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test48() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.gcd(5, 0);
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test49() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.gcd(0, 0);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test50() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.gcd((-1), 5);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test51() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.lcm(4, 6);
        assertEquals(12, int0);
    }

    @Test(timeout = 4000)
    public void test52() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.lcm(7, 11);
        assertEquals(77, int0);
    }

    @Test(timeout = 4000)
    public void test53() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.lcm(0, 5);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test54() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.signum(10);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test55() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.signum((-10));
        assertEquals((-1), int0);
    }

    @Test(timeout = 4000)
    public void test56() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.signum(0);
        assertEquals(0, int0);
    }

    @Test(timeout = 4000)
    public void test57() throws Throwable {
        Calculator calculator0 = new Calculator();
        int[] intArray0 = new int[3];
        intArray0[0] = 1;
        intArray0[1] = 2;
        intArray0[2] = 3;
        double double0 = calculator0.average(intArray0);
        assertEquals(2.0, double0, 0.01);
    }

    @Test(timeout = 4000)
    public void test58() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.average((int[]) null);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test59() throws Throwable {
        Calculator calculator0 = new Calculator();
        int[] intArray0 = new int[0];
        try {
            calculator0.average(intArray0);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test60() throws Throwable {
        Calculator calculator0 = new Calculator();
        String string0 = calculator0.classify(5);
        assertEquals("positivo", string0);
    }

    @Test(timeout = 4000)
    public void test61() throws Throwable {
        Calculator calculator0 = new Calculator();
        String string0 = calculator0.classify((-5));
        assertEquals("negativo", string0);
    }

    @Test(timeout = 4000)
    public void test62() throws Throwable {
        Calculator calculator0 = new Calculator();
        String string0 = calculator0.classify(0);
        assertEquals("zero", string0);
    }

    @Test(timeout = 4000)
    public void test63() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isInRange(5, 1, 10);
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
    public void test64() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isInRange(0, 1, 10);
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test65() throws Throwable {
        Calculator calculator0 = new Calculator();
        boolean boolean0 = calculator0.isInRange(11, 1, 10);
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
    public void test66() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.isInRange(5, 10, 1);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test(timeout = 4000)
    public void test67() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.clamp(5, 1, 10);
        assertEquals(5, int0);
    }

    @Test(timeout = 4000)
    public void test68() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.clamp(0, 1, 10);
        assertEquals(1, int0);
    }

    @Test(timeout = 4000)
    public void test69() throws Throwable {
        Calculator calculator0 = new Calculator();
        int int0 = calculator0.clamp(15, 1, 10);
        assertEquals(10, int0);
    }

    @Test(timeout = 4000)
    public void test70() throws Throwable {
        Calculator calculator0 = new Calculator();
        try {
            calculator0.clamp(5, 10, 1);
            fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }
}
