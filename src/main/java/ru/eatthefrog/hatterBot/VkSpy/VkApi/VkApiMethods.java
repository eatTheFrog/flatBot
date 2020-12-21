package ru.eatthefrog.hatterBot.VkSpy.VkApi;

import com.google.gson.Gson;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;

@Component
public class VkApiMethods {
    final String PREFIX = "https://api.vk.com/method/";
    @Autowired
    HTTPGetterable httpGetterable;
    @Autowired
    Gson gson;

    public String usersGet(int profileId, String token, String[] fields) {
        return "";
    }
    public boolean isOnline(int profileId, String token) {
        String request = this.PREFIX+"users.get?"+"user_ids="+String.valueOf(profileId)+"&fields=online"+"&access_token="+token+"&v=5.126";
        String response = httpGetterable.doRequest(request);
        VkUsersGetResponse vkUsersGetResponse = gson.fromJson(response,VkUsersGetResponse.class);
        return vkUsersGetResponse.response[0].online == 1;
    }
}
