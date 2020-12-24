package ru.eatthefrog.hatterBot.VkSpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethodsImplementator;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkApiTokenInstance;

@Component
public class VkNameProvider {
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
