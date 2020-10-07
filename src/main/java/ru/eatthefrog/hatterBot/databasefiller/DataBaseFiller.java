package ru.eatthefrog.hatterBot.databasefiller;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataBaseFiller {
    Date lastTimeUpdated;

    public int getSecondsSinceUpdate() {
        return 0;
    }

    public void updateFlatDataBase() {

    }
}
