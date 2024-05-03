package dataStructures;



public enum Operator {
    PLUS("plus"),
    MINUS("minus"),
    MULTIPLY("multiply"),
    DIVIDE("divide");

    private String operator;

    Operator(String operator) {
        this.operator = operator;
    }

        public static Operator fromString(String operator) {
            for (Operator op : Operator.values()) {
                if (op.name().equals(operator.toUpperCase())) {
                    return op;
                }
            }
            throw new IllegalArgumentException("Invalid operator: " + operator);
        }
}
