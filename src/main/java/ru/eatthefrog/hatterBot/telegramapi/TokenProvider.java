package ru.eatthefrog.hatterBot.telegramapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.simpleAES.AES;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

@Component
public class TokenProvider {
    @Autowired
    AES aes;
    String result = "";
    InputStream inputStream;

    public String askForPassword() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите пароль");
        return in.nextLine();
    }

    public String getEncryptedToken() throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "token.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            result = prop.getProperty("token");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null)
                inputStream.close();
        }
        return result;
    }

    public Token getToken() {
        System.out.println(aes);
        String encryptedToken = null;
        try {
             encryptedToken = getEncryptedToken();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String password = askForPassword();
        try {
            return new Token(aes.decrypt(encryptedToken, password), true);
        }
        catch (Exception e) {
            return new Token("", false);
        }
    }
}
