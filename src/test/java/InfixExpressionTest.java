import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class InfixExpressionTest {

    private Faker faker = new Faker();

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenEmptyStringPassed() {
        InfixExpression infixExpression = new InfixExpression("");
    }

    @Test
    public void shouldBeValidWhenContainsOneNumber() {
        InfixExpression infixExpression = new InfixExpression(randomDigit(true));
        assertThat(infixExpression.isValid()).isTrue();
    }

    @Test
    public void shouldBeInvalidWhenContainsJustOneParenthesis() {
        InfixExpression infixExpression = new InfixExpression(randomParenthesis());
        assertThat(infixExpression.isValid()).isFalse();
    }

    @Test
    public void shouldBeInvalidWhenContainsJustOneOperator() {
        InfixExpression infixExpression = new InfixExpression(randomOperator());
        assertThat(infixExpression.isValid()).isFalse();
    }

    @Test
    public void shouldBeInvalidWhenNumberToRightOfClosingParenthesis() {
        InfixExpression infixExpression = new InfixExpression(faker.number().digit() + faker.regexify("\\){1}\\d"));
        assertThat(infixExpression.isValid()).isFalse();
    }

    @Test
    public void shouldBeInvalidWhenOperatorToRightOfOpenParenthesis() {
        InfixExpression infixExpression = new InfixExpression(faker.number().digit() + faker.regexify("\\){1}\\d"));
        assertThat(infixExpression.isValid()).isFalse();
    }

    @Test
    public void shouldBeInvalidWhenValidExpressionContainsSpaces() {
        InfixExpression infixExpression = new InfixExpression(faker.number().digit() + faker.regexify("\\){1}\\d"));
        assertThat(infixExpression.isValid()).isFalse();
    }

    private String randomOperator() {
        return faker.options().option("+", "-", "*", "/");
    }

    private String randomParenthesis() {
        return faker.options().option("(", ")");
    }

    private String randomNumber() {
        return String.valueOf(faker.number().randomNumber());
    }

    private String randomDigit(Boolean couldBeZero) {
        return couldBeZero ?
            String.valueOf(faker.number().randomDigitNotZero()) : faker.number().digit();
    }
}
