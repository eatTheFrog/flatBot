package ru.eatthefrog.hatterBot.DialogStateManager;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoUserStatesManager;

import javax.annotation.PostConstruct;
import java.util.Dictionary;
import java.util.Enumeration;

@Component
@Aspect
public class UserDialogStatePositionMongoSaverAspect {
    int dialogStatePositionUpdateFrequency = 3600000;
    int dialogStatePositionMaxLive = 3600000;
    long lastTimeUpdate;
    @Autowired
    DialogStateManager dialogStateManager;

    @Autowired
    MongoUserStatesManager mongoUserStatesManager;

    @PostConstruct
    void initLastTimeUpdateToNow() {
        lastTimeUpdate = System.currentTimeMillis();
    }
    @Before("execution(* statefulMessageProcess(..))")
    void checkNeedAndSave() {
        if (System.currentTimeMillis() - lastTimeUpdate > dialogStatePositionUpdateFrequency) {
            saveOldUserDialogStatePositions();
            lastTimeUpdate = System.currentTimeMillis();
        }
    }
    void saveOldUserDialogStatePositions() {
        System.out.println("HELLO");
        Dictionary<Integer, UserDialogStatePosition> dictionary = dialogStateManager.getStatePositionDict();
        Enumeration<UserDialogStatePosition> positionEnumeration = dictionary.elements();
        while(positionEnumeration.hasMoreElements()){
            UserDialogStatePosition userDialogStatePositionTemp = positionEnumeration.nextElement();
            if (isUserDialogStatePositionOld(userDialogStatePositionTemp)) {
                mongoUserStatesManager.saveStatePosition(userDialogStatePositionTemp);
                dictionary.remove(userDialogStatePositionTemp.chatID);
            }
        }
    }
    Boolean isUserDialogStatePositionOld(UserDialogStatePosition userDialogStatePosition) {
        return System.currentTimeMillis() - userDialogStatePosition.lastTimeTouched > dialogStatePositionMaxLive;
    }
}
