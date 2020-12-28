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
    String[] finalStringNames = {"PingSpecificCountState","NmapDefaultState","RegistrationAskLoginDialogState","SpyVkState","NmapState","SpyVkAddSpyFriendsState","NmapPortState","NotYetImplementedState","StartingState","UnloggedMainMenu","LoggedMainMenu","LogoutState","VkTokenAskState","UnknownDialogState","InfoState","PingState","LoginAskPasswordDialogState","GetSpyListState","PingBurstState","SpyVkAddSpyOnline","LoginAskLoginDialogState","EchoState","SpyVkAddSpyWall","RegistrationAskPasswordDialogState","NetworkingToolsMenuState","NmapAllState","PingSpecificCountState","NmapDefaultState","RegistrationAskLoginDialogState","SpyVkState","NmapState","SpyVkAddSpyFriendsState","NmapPortState","NotYetImplementedState","StartingState","UnloggedMainMenu","LoggedMainMenu","LogoutState","VkTokenAskState","UnknownDialogState","InfoState","PingState","LoginAskPasswordDialogState","GetSpyListState","PingBurstState","SpyVkAddSpyOnline","LoginAskLoginDialogState","EchoState","SpyVkAddSpyWall","RegistrationAskPasswordDialogState","NetworkingToolsMenuState","NmapAllState","RemoveFromSpyState","AddVkTokenState"};

    @PostConstruct
    void loadFinalStrings() {
        ClassLoader cl = Application.class.getClassLoader();


        for (String name: this.finalStringNames) {

            var inputStream = cl.getResourceAsStream("finalStrings/"+name);
            String s = null;
            try {
                s = IOUtils.toString(inputStream);
            }
            catch (IOException e) {
                s = "";
            }

            finalStringsDictionary.put(
                    name,
                    s);
        }
    }

    public String getFinalString(String identifierString) {
        return finalStringsDictionary.get(identifierString);
    }
}
