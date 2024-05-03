package be.calculator;

import dataStructures.Operator;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.BiFunction;

import static be.calculator.Constants.*;

@Service
public class CalculatorService {

    private static final Map<Operator, BiFunction<Integer, Integer, Integer>> OPERATIONS = Map.of(
            Operator.PLUS, Integer::sum,
            Operator.MINUS, (a, b) -> a - b,
            Operator.MULTIPLY, (a, b) -> a * b,
            Operator.DIVIDE, CalculatorService::divide
    );

    private static final Map<Operator, String> SYMBOLS = Map.of(
            Operator.PLUS, PLUS_SYMBOL,
            Operator.MINUS, MINUS_SYMBOL,
            Operator.MULTIPLY, MULTIPLY_SYMBOL,
            Operator.DIVIDE, DIVIDE_SYMBOL
    );

    private static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero isn't a valid action");
        }
        return a / b;
    }

    public int calculate(Operator operator, int left, int right) {
        return OPERATIONS.get(operator).apply(left, right);
    }

    public String getSymbol(Operator operator) {
        return SYMBOLS.get(operator);
    }
}
