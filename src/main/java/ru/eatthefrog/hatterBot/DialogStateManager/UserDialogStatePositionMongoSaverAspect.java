package ru.eatthefrog.hatterBot.DialogStateManager;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.eatthefrog.hatterBot.MongoDBOperator.MongoUserStatesManager;

import javax.annotation.PostConstruct;

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
//        System.out.println("HELLO");
//        AbstractMap<Integer, DialogStatePosition> dictionary = dialogStateManager.getStatePositionDict();
//        Enumeration<DialogStatePosition> positionEnumeration = dictionary.values();
//        while(positionEnumeration.hasMoreElements()){
//            DialogStatePosition dialogStatePositionTemp = positionEnumeration.nextElement();
//            if (isUserDialogStatePositionOld(dialogStatePositionTemp)) {
//                mongoUserStatesManager.saveStatePosition(dialogStatePositionTemp);
//                dictionary.remove(dialogStatePositionTemp.chatID);
//            }
//        }
    }
    Boolean isUserDialogStatePositionOld(DialogStatePosition dialogStatePosition) {
        return System.currentTimeMillis() - dialogStatePosition.lastTimeTouched > dialogStatePositionMaxLive;
    }
}
