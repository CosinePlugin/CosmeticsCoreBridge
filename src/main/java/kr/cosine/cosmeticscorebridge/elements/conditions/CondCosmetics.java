package kr.cosine.cosmeticscorebridge.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;

public class CondCosmetics extends PropertyCondition<Cosmetics> {

    static {
        register(
            CondCosmetics.class,
            PropertyType.BE,
            "cosmetics",
            "cosmetic"
        );
    }

    @Override
    public boolean check(Cosmetics cosmetics) {
        return cosmetics.isNotNull;
    }

    @Override
    protected String getPropertyName() {
        return "cosmetics";
    }
}
