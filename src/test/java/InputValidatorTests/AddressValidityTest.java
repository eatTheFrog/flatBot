package InputValidatorTests;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.eatthefrog.hatterBot.Tools.Validator.InputValidator;

public class AddressValidityTest extends Root {

    @BeforeClass
    public static void setUp() {
        setValidable(InputValidator::isAddressValid);
    }

    @Test
    public void testDotlessAddresses(){
        inputs = new String[]{"asdf", "123", "6gyu", "hahahahahahe", "8uj9"};
        checkValidityIsFalse();
    }

    @Test
    public void testAddressesWithSpaces(){
        inputs = new String[]{"gitlab.com github.com"};
        checkValidityIsFalse();
    }

    @Test
    public void testDotsStrings(){
        inputs = new String[]{".", "..", "..."};
        checkValidityIsFalse();
    }
    
    @Test
    public void testAddressWithOneDot(){
        inputs = new String[]{"vk.com", "ya.ru", "google.org", "wikipedia.org"};
        checkValidityIsTrue();
    }

    @Test
    public void testAddressWithTwoDots(){
        inputs = new String[]{"ru.wikipedia.org", "wiki.archlinux.org"};
        checkValidityIsTrue();
    }

    @Test
    public void testIp4Adresses(){
        inputs = new String[]{"198.123.55.2", "0.0.0.0"};
        checkValidityIsTrue();
    }
}
