package ru.eatthefrog.hatterBot.ArrayChangesComparator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class ArrayChangesComparator {
    public ArrayChangesCompareResult getCompareResult(
            Integer[] friendsWas,
            Integer[] friendsNow
    ) {
        var compareResult = new ArrayChangesCompareResult();
        HashSet<Integer> friendsDeletedHash = new HashSet<Integer>(
                Arrays.asList(friendsWas)
        );
        HashSet<Integer> friendsNewHash = new HashSet<Integer>(
                Arrays.asList(friendsNow)
        );
        var cp = (HashSet<Integer>) friendsDeletedHash.clone();
        friendsDeletedHash.removeAll(friendsNewHash);
        friendsNewHash.removeAll(cp);

        Integer[] friendsDeletedArray = new Integer[friendsDeletedHash.size()];
        Integer[] friendsNewArray = new Integer[friendsNewHash.size()];
        friendsDeletedHash.toArray(friendsDeletedArray);
        friendsNewHash.toArray(friendsNewArray);
        if (friendsDeletedArray.length == 0 && friendsNewArray.length == 0) {
            return compareResult;
        }
        compareResult.newFriends = friendsNewArray;
        compareResult.deletedFriends = friendsDeletedArray;
        compareResult.isChanged = true;
        return compareResult;
    }
}
