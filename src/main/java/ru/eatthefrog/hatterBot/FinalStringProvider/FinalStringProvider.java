package ru.eatthefrog.hatterBot.FinalStringProvider;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import ru.eatthefrog.hatterBot.Application;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.Hashtable;

@Component
public class FinalStringProvider {
    Dictionary<String, String> finalStringsDictionary = new Hashtable<>();
    String[] finalStringNames = {"PingSpecificCountState","NmapDefaultState","RegistrationAskLoginDialogState","SpyVkState","NmapState","SpyVkAddSpyFriendsState","NmapPortState","NotYetImplementedState","StartingState","UnloggedMainMenu","LoggedMainMenu","LogoutState","VkTokenAskState","UnknownDialogState","InfoState","PingState","LoginAskPasswordDialogState","GetSpyListState","PingBurstState","SpyVkAddSpyOnline","LoginAskLoginDialogState","EchoState","SpyVkAddSpyWall","RegistrationAskPasswordDialogState","NetworkingToolsMenuState","NmapAllState","PingSpecificCountState","NmapDefaultState","RegistrationAskLoginDialogState","SpyVkState","NmapState","SpyVkAddSpyFriendsState","NmapPortState","NotYetImplementedState","StartingState","UnloggedMainMenu","LoggedMainMenu","LogoutState","VkTokenAskState","UnknownDialogState","InfoState","PingState","LoginAskPasswordDialogState","GetSpyListState","PingBurstState","SpyVkAddSpyOnline","LoginAskLoginDialogState","EchoState","SpyVkAddSpyWall","RegistrationAskPasswordDialogState","NetworkingToolsMenuState","NmapAllState"};

    @PostConstruct
    void loadFinalStrings() {
        try {

            ClassLoader cl = Application.class.getClassLoader();
            File[] files = ResourceUtils.getFile("classpath:finalStrings").listFiles();
            if (files == null)
            {
                System.out.println("ResourceUtils.getFile().listFiles() returns null and we don't know why." );
                return;
            }


            for (String name: this.finalStringNames) {
                var inputStream = cl.getResourceAsStream("finalStrings/"+name);
                if (inputStream == null) {
                    return;
                }
                finalStringsDictionary.put(
                        name,
                        IOUtils.toString(inputStream));
            }

        }
        catch (IOException e) {
            System.out.println("Failed to read resources/finalStrings/");
        }
    }

    public String getFinalString(String identifierString) {
        return finalStringsDictionary.get(identifierString);
    }
}
