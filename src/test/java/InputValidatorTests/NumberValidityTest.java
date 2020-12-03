package InputValidatorTests;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.eatthefrog.hatterBot.Tools.InputValidator;


public class NumberValidityTest extends RootTest {

    @BeforeClass
    public static void setUp(){
        setValidable(InputValidator::isNumberValid);
    }

    @Test
    public void testNegativeNumbers(){
        inputs = new String[]{"-20", "-1", "-3"};
        checkValidityIsFalse();
    }

    @Test
    public void testZero(){
        inputs = new String[]{"0"};
        checkValidityIsFalse();
    }

    @Test
    public void testPositiveIntegers(){
        inputs = new String[]{"1", "2", "3", "40", "123", "254", "8701"};
        checkValidityIsTrue();
    }
}
