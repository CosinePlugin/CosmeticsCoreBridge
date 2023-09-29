package kr.cosine.cosmeticscorebridge.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import kr.cosine.cosmeticscorebridge.CosmeticsCoreBridge;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;
import kr.cosine.cosmeticscorebridge.elements.effects.util.EffectUtils;
import kr.cosine.cosmeticscorebridge.service.CosmeticsService;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class EffEquipCosmetics extends Effect {

    static {
        Skript.registerEffect(EffEquipCosmetics.class, "equip %object% to %player%");
    }

    private Expression<?> expressionCosmetics;
    private Expression<Player> expressionPlayer;

    private final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private final CosmeticsService cosmeticsService = plugin.getCosmeticsService();

    @Override
    protected void execute(Event event) {
        if (expressionCosmetics == null) return;
        if (expressionPlayer == null) return;
        Object cosmetics = expressionCosmetics.getSingle(event);
        String cosmeticsKey = EffectUtils.getCosmeticsKey(cosmetics);
        Player player = expressionPlayer.getSingle(event);
        cosmeticsService.equipCosmetics(cosmeticsKey, player);
    }

    @Override
    public String toString(Event event, boolean debug) {
        return expressionPlayer.toString(event, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        expressionCosmetics = exprs[0];
        expressionPlayer = (Expression<Player>) exprs[1];
        return true;
    }
}
