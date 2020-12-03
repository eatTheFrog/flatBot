package ru.eatthefrog.hatterBot.Tools;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputValidator {
    private static Pattern addressPattern = Pattern.compile("(^[^\\.\\s]+\\.[^\\.\\s]+(\\.[^\\.\\s]+)*$)");
    private static Pattern numberPattern = Pattern.compile("^[+]?[1-9]+\\d*$");

    public static boolean isAddressValid(String address){
        return addressPattern.matcher(address).find();
    }

    public static boolean isNumberValid(String number){
        return numberPattern.matcher(number).find();
    }

    public static boolean checkPingBurstInput(String input){
        return isAddressValid(input);
    }

    public static boolean checkPingSpecificInput(String input){
        String[] components = input.split(" ");
        if (components.length != 2){
            return false;
        }
        return isAddressValid(components[0]) && isNumberValid(components[1]);
    }
}
