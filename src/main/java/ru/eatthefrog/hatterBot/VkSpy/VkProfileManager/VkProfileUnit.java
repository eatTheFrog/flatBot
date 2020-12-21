package ru.eatthefrog.hatterBot.VkSpy.VkProfileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethods;

@Component
@Scope("prototype")
public class VkProfileUnit {

    boolean isOnline = false;
    @Autowired
    VkApiMethods vkApiMethods;

    public void buildPage(int vkProfileId, String token) {
        this.isOnline = vkApiMethods.isOnline(vkProfileId, token);
    }
    public boolean getIsOnline() {
        return this.isOnline;
    }
    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
}
