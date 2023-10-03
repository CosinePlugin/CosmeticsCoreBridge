package kr.cosine.cosmeticscorebridge.elements.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import kr.cosine.cosmeticscorebridge.CosmeticsCoreBridge;
import kr.cosine.cosmeticscorebridge.service.CosmeticsService;
import org.bukkit.entity.Player;

public class CondisInWardrobe extends PropertyCondition<Player> {

    static {
        register(
            CondisInWardrobe.class,
            PropertyType.BE,
            "in wardrobe",
            "player"
        );
    }

    private final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private final CosmeticsService cosmeticsService = plugin.getCosmeticsService();

    @Override
    public boolean check(Player player) {
        return cosmeticsService.isInWardrobe(player);
    }

    @Override
    protected String getPropertyName() {
        return "in wardrobe";
    }
}
