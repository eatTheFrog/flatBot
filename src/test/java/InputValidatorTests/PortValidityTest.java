package InputValidatorTests;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.eatthefrog.hatterBot.Tools.Validator.InputValidator;

public class PortValidityTest extends Root {
    @BeforeClass
    public static void setUp() {
        setValidable(InputValidator::isPortValid);
    }

    @Test
    public void testNegativeNumber(){
        inputs = new String[]{"-1", "-2", "-123"};
        checkValidityIsFalse();
    }

    @Test
    public void testZero(){
        inputs = new String[]{"0"};
        checkValidityIsTrue();
    }

    @Test
    public void testNotNumbers(){
        inputs = new String[]{"abd", "qwe, w", "asfmkafs12"};
        checkValidityIsFalse();
    }

    @Test
    public void testValidPorts(){
        inputs = new String[]{"0", "123", "88", "27053"};
        checkValidityIsTrue();
    }

    @Test
    public void testImpossiblePort(){
        inputs = new String[]{"65535", "10000000", "6123123"};
        checkValidityIsFalse();
    }
}
