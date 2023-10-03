package kr.cosine.cosmeticscorebridge.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;

public class CondIsCosmetics extends PropertyCondition<Cosmetics> {

    static {
        register(
            CondIsCosmetics.class,
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
