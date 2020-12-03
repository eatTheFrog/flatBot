package ru.eatthefrog.hatterBot.Tools.Validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputValidator {
    private static Pattern addressPattern = Pattern.compile("(^[^\\.\\s]+\\.[^\\.\\s]+(\\.[^\\.\\s]+)*$)");
    private static Pattern numberPattern = Pattern.compile("^[+]?[1-9]+\\d*$");
    private static Pattern portPattern = Pattern.compile("^\\d{1,5}$");

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

    public static boolean isPortValid(String port){
        if (! portPattern.matcher(port).find()){
            return false;
        }
        int portNumber = Integer.parseInt(port);
        return portNumber >= 0 && portNumber <= 65534;
    }

    public static boolean checkNmapDefaultInput(String input){return isAddressValid(input);}

    public static boolean checkNmapPort(String input){
        String[] components = input.split(" ");
        if (components.length != 2){
            return false;
        }
        return isAddressValid(components[0]) && isPortValid(components[1]);
    }

    public static boolean checkNmapAll(String input){ return isAddressValid(input);}
}
