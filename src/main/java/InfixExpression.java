import java.util.regex.Pattern;

public class InfixExpression {

    private String expression;

    public InfixExpression(String expression) {
        if(expression.isEmpty()) {
            throw new RuntimeException("Invalid Expression");
        }

        this.expression = expression;
    }

    public Boolean isValid() {

        if(Pattern.matches("(\\({1})|(\\){1})|", this.expression)) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
