package ru.eatthefrog.hatterBot.FriendsChangesComparator;

import java.util.ArrayList;

public class FriendsChangesCompareResult {
    boolean isChanged = false;
    Integer[] newFriends;
    Integer[] deletedFriends;

    public boolean isChanged() {
        return this.isChanged;
    }
    public Integer[] getNewFriends() {
        return this.newFriends;
    }
    public Integer[] getDeletedFriends() {
        return this.deletedFriends;
    }
}
