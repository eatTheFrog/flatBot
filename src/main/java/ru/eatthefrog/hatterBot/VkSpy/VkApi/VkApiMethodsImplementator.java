package ru.eatthefrog.hatterBot.VkSpy.VkApi;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.HTTPGetter.HTTPGetterable;
import ru.eatthefrog.hatterBot.PropertiesProvider;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkFriendsGet.VkFriendsGetResponse;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkUsersGet.VkUsersGetResponse;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkWallGet.VkWallGetResponse;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkWallGetById.VkWallGetByIdResponse;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkApiTokenInstance;

import javax.annotation.PostConstruct;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class VkApiMethodsImplementator {
    final String PREFIX = "https://api.vk.com/method/";
    boolean loggingRequests = false;

    @Autowired
    HTTPGetterable httpGetterable;
    @Autowired
    Gson gson;
    @Autowired
    PropertiesProvider propertiesProvider;
    @PostConstruct
    void setLogMode() {
        this.loggingRequests = Boolean.parseBoolean(propertiesProvider.getProperty("log.properties",
                "log_vk_api_requests"));
    }
    public String vkApiRequest(String methodName,
                               String args,
                               VkApiTokenInstance tokenInstance)  {

        tokenInstance.getLock().lock();
        while (!tokenInstance.isReady()) {
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tokenInstance.makeCooldown();
        tokenInstance.getLock().unlock();

        String token = tokenInstance.getValue();
        String request =
                this.PREFIX+methodName+args+"&access_token="+token+"&v=5.126";


        if (this.loggingRequests)
            System.out.println("DONE");

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
    public VkWallGetByIdResponse wallGetById(int profileId,
                                             VkApiTokenInstance tokenInstance,
                                             Integer postId
                                             ) throws TooManyRequestsException {
        String response = this.vkApiRequest("wall.getById?",
                "posts="+String.valueOf(profileId)+"_"+String.valueOf(postId),
                tokenInstance);
        if (response == null) {
            throw new TooManyRequestsException();
        }
        System.out.println(response);
        return gson.fromJson(response,VkWallGetByIdResponse.class);
    }
    public VkWallGetResponse wallGet(int profileId, VkApiTokenInstance tokenInstance) throws TooManyRequestsException {
        String response = this.vkApiRequest("wall.get?",
                "owner_id="+String.valueOf(profileId),
                tokenInstance);
        if (response == null) {
            throw new TooManyRequestsException();
        }
        return gson.fromJson(response,VkWallGetResponse.class);
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
