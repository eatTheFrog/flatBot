package ru.eatthefrog.hatterBot.VkSpy.VkProfileManager;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.SpringConfiguration;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class VkProfileUnitManager {
    AbstractMap<Integer, VkProfileUnit> vkProfiles = new ConcurrentHashMap<>();
    public VkProfileUnit getVkProfileState(int profileId, String token) {
        VkProfileUnit vkProfileUnit = this.vkProfiles.get(profileId);
        if (vkProfileUnit != null) {
            return vkProfileUnit;
        }
        else {
            return this.buildProfile(profileId, token);
        }
    }
    public VkProfileUnit buildProfile(int vkProfileId, String token) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfiguration.class
        );
        VkProfileUnit vkProfileUnit = context.getBean(VkProfileUnit.class);
        vkProfileUnit.buildPage(vkProfileId, token);
        this.vkProfiles.put(vkProfileId,
                vkProfileUnit);
        return vkProfileUnit;
    }
}
