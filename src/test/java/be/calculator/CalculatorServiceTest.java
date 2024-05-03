package be.calculator;

import dataStructures.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorServiceTest {
    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testAddition() {
        int result = calculatorService.calculate(Operator.PLUS,10, 20);
        assertEquals(30, result);
    }

    @Test
    public void testSubtraction() {
        int result = calculatorService.calculate(Operator.MINUS,10, 20);
        assertEquals(-10, result);
    }

    @Test
    public void testMultiplication() {
        int result = calculatorService.calculate(Operator. MULTIPLY,10, 20);
        assertEquals(200, result);
    }

    @Test
    public void testDivision() {
        int result = calculatorService.calculate(Operator.DIVIDE,20, 10);
        assertEquals(2, result);
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.calculate(Operator.DIVIDE, 20, 0));
    }
}
