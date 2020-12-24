package ru.eatthefrog.hatterBot.ArrayChangesComparator;

public class ArrayChangesCompareResult {
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
