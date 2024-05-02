package be.calculator;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.BiFunction;

import static be.calculator.Constants.*;

@Service
public class CalculatorService {

    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = Map.of(
            PLUS, Integer::sum,
            MINUS, (a, b) -> a - b,
            MULTIPLY, (a, b) -> a * b,
            DIVIDE, CalculatorService::divide
    );

    private static final Map<String, String> SYMBOLS = Map.of(
            PLUS, PLUS_SYMBOL,
            MINUS, MINUS_SYMBOL,
            MULTIPLY, MULTIPLY_SYMBOL,
            DIVIDE, DIVIDE_SYMBOL
    );

    private static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero isn't a valid action");
        }
        return a / b;
    }

    public int calculate(String operator, int left, int right) {
        return OPERATIONS.get(operator).apply(left, right);
    }

    public String getSymbol(String operator) {
        return SYMBOLS.get(operator);
    }
}
