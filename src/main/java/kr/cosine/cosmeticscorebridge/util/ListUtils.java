package kr.cosine.cosmeticscorebridge.util;

import java.util.List;
import java.util.Random;

public class ListUtils {

    private static final Random random = new Random();

    public static <T> T random(List<T> list) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
