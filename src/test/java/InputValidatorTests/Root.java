package InputValidatorTests;

import org.junit.Test;
import ru.eatthefrog.hatterBot.Tools.Validator.Validable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public abstract class Root {
    protected static String[] inputs;
    protected static Validable v;

    public static void setValidable(Validable input){
        v = input;
    }

    public void checkValidityIsTrue(){
        for (String address: inputs) {
            assertTrue(v.validate(address));
        }
    }

    public void checkValidityIsFalse(){
        for (String address: inputs){
            assertFalse(v.validate(address));
        }
    }

    @Test
    public void testEmptyString(){
        inputs = new String[]{""};
        checkValidityIsFalse();
    }

    @Test
    public void testWhiteSpaces(){
        inputs = new String[]{"\n", "\t", " "};
        checkValidityIsFalse();
    }

    @Test
    public void testMaliciousInput(){
        inputs = new String[]{"vk.com && sudo rm -R /", "ya.ru && cd /"};
        checkValidityIsFalse();
    }
}
