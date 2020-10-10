package ru.eatthefrog.hatterBot.FinalStringProvider;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ru.eatthefrog.hatterBot.DebugPrinter;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.Hashtable;

@Component
public class FinalStringProvider {


    Dictionary<String, String> finalStringsDictionary = new Hashtable<>();
    @Autowired
    DebugPrinter debugPrinter;
    @PostConstruct
    void loadFinalStrings() {
        try {
            File[] files = ResourceUtils.getFile("classpath:finalStrings").listFiles();
            if (files == null)
            {
                System.out.println("ResourceUtils.getFile().listFiles() returns null and we don't know why." );
                return;
            }

            for (File file: files) {
                finalStringsDictionary.put(
                        file.getName(),
                        FileUtils.readFileToString(file, StandardCharsets.UTF_8));
            }

        }
        catch (IOException e) {
            System.out.println("Failed to read resources/finalStrings/");
        }
    }

    public String getFinalString(String identifierString) {
        debugPrinter.print(identifierString, this);
        return finalStringsDictionary.get(identifierString);
    }
}
