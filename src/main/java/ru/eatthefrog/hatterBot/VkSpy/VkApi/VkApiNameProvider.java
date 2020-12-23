package ru.eatthefrog.hatterBot.VkSpy.VkApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.SpringConfiguration;
import ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager.VkApiTokenInstance;
import ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager.VkUserTokenManager;

@Component
public class VkApiNameProvider {
    @Autowired
    VkApiMethodsImplementator vkApiMethodsImplementator;
    public String getName(int vkUserId,
                          VkApiTokenInstance tokenInstance) {
        try {
            var x = vkApiMethodsImplementator.usersGet(
                    vkUserId,
                    tokenInstance,
                    ""

            );
            return String.format("%s %s", x.response[0].name, x.response[0].surname);
        }
        catch (TooManyRequestsException e) {
            e.printStackTrace();
            return "";
        }
    }
}
