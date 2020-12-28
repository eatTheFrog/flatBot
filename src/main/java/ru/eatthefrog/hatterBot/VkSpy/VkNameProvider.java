package ru.eatthefrog.hatterBot.VkSpy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.TooManyRequestsException;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethodsImplementator;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkApiTokenInstance;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VkNameProvider {
    AbstractMap<Integer, String> names = new ConcurrentHashMap<>();
    @Autowired
    VkApiMethodsImplementator vkApiMethodsImplementator;
    public String getName(int vkUserId,
                          VkApiTokenInstance tokenInstance) {
        String name = this.names.get(vkUserId);
        if (name != null) {
            return name;
        }
        try {
            var x = vkApiMethodsImplementator.usersGet(
                    vkUserId,
                    tokenInstance,
                    ""

            );
            String nameFromApi = String.format("%s %s", x.response[0].name, x.response[0].surname);
            this.names.put(vkUserId, nameFromApi);
            return nameFromApi;
        }
        catch (TooManyRequestsException e) {
            e.printStackTrace();
            return "";
        }
    }
}
