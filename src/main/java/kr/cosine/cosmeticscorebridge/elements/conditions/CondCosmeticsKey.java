package kr.cosine.cosmeticscorebridge.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import kr.cosine.cosmeticscorebridge.CosmeticsCoreBridge;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;
import kr.cosine.cosmeticscorebridge.service.CosmeticsService;

public class CondCosmeticsKey extends PropertyCondition<String> {

    static {
        register(
            CondCosmeticsKey.class,
            PropertyType.BE,
            "cosmetics",
            "string"
        );
    }

    private final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private final CosmeticsService cosmeticsService = plugin.getCosmeticsService();

    @Override
    public boolean check(String input) {
        return cosmeticsService.isCosmetics(input);
    }

    @Override
    protected String getPropertyName() {
        return "cosmetics";
    }
}
