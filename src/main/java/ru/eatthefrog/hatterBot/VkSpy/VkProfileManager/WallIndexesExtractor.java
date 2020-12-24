package ru.eatthefrog.hatterBot.VkSpy.VkProfileManager;

import org.springframework.stereotype.Component;

@Component
public class WallIndexesExtractor {
    public Integer[] extractWallIndexes(WallPost[] posts) {
        int l = posts.length;
        Integer[] wallIndexes = new Integer[l];
        for (int i = 0; i < l; i++) {
            wallIndexes[i] = posts[i].id;
        }
        return wallIndexes;
    }
}
