package be.calculator;

import dataStructures.SimpleOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@SpringBootApplication
@RestController
public class OperationController {

    public static void main(String[] args) {
		SpringApplication.run(OperationController.class, args);
	}
    private final CalculatorService calculatorService;

    @Autowired
    public OperationController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody SimpleOperation operation) {

        if(Objects.isNull(operation.getOperator()) || Objects.isNull(operation.getLeft()) || Objects.isNull(operation.getRight())) {
            return ResponseEntity.badRequest().body("Request body must have exactly three parameters: operator, left, and right");
        }
        try{
            String stringResponse = createStringResponse(operation);
            return ResponseEntity.ok(stringResponse);
        } catch (ArithmeticException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred while processing the request");
        }


    }

    private String createStringResponse(SimpleOperation operation) {
        int result = calculatorService.calculate(operation.getOperator(), operation.getLeft(), operation.getRight());
        String symbol = calculatorService.getSymbol(operation.getOperator());
        return operation.getLeft() + symbol + operation.getRight() + "=" + result;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidRequestBody(HttpMessageNotReadableException ex) {
        if (ex.getRootCause() instanceof IllegalArgumentException) {
            return ResponseEntity.badRequest().body( ex.getRootCause().getMessage());
        }
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
