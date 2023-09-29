package kr.cosine.cosmeticscorebridge.elements.effects.util;

import kr.cosine.cosmeticscorebridge.data.Cosmetics;

public class EffectUtils {

    public static String getCosmeticsKey(Object object) {
        String cosmeticsKey;
        if (object instanceof Cosmetics) {
            cosmeticsKey = ((Cosmetics) object).getKey();
        } else {
            cosmeticsKey = (String) object;
        }
        return cosmeticsKey;
    }
}
