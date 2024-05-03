package dataStructures;


import lombok.Getter;

import java.util.Objects;

@Getter
public class SimpleOperation {

    private Operator operator;

    private Integer left;

    private Integer right;

    public SimpleOperation(String operator, Integer left, Integer right) {
        this.operator = Operator.fromString(operator);
        this.left = left;
        this.right = right;
    }
}
