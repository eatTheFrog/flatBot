package ru.eatthefrog.hatterBot.VkSpy.VkApi;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkFriendsGet.VkFriendsGetResponse;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkUsersGet.VkUsersGetResponse;
import ru.eatthefrog.hatterBot.VkSpy.VkUserStatesManager.VkApiTokenInstance;

@Component
public class VkApiMethodsImplementator {
    final String PREFIX = "https://api.vk.com/method/";
    @Autowired
    HTTPGetterable httpGetterable;
    @Autowired
    Gson gson;
    public String vkApiRequest(String methodName,
                               String args,
                               VkApiTokenInstance tokenInstance)  {
        String token = tokenInstance.getValue();
        String request =
                this.PREFIX+methodName+args+"&access_token="+token+"&v=5.126";
        tokenInstance.makeCooldown();
        return httpGetterable.doRequest(request);
    }
    public VkFriendsGetResponse friendsGet(int profileId, VkApiTokenInstance tokenInstance) throws TooManyRequestsException {
        String response = this.vkApiRequest("friends.get?",
                "user_id="+String.valueOf(profileId),
                tokenInstance);
        if (response == null) {
            throw new TooManyRequestsException();
        }
        return gson.fromJson(response,VkFriendsGetResponse.class);
    }

    public VkUsersGetResponse usersGet(int profileId, VkApiTokenInstance tokenInstance, String fields) throws TooManyRequestsException {
        String response = this.vkApiRequest("users.get?",
                "user_ids="+String.valueOf(profileId)+"&fields="+fields,
                tokenInstance);
        System.out.println(response);
        if (response == null) {
            throw new TooManyRequestsException();
        }
        return gson.fromJson(response,VkUsersGetResponse.class);
    }
    public boolean isOnline(int profileId, VkApiTokenInstance tokenInstance) throws TooManyRequestsException {

        VkUsersGetResponse vkUsersGetResponse = this.usersGet(profileId,
                tokenInstance,
                "online");

        return vkUsersGetResponse.response[0].online == 1;
    }
}
