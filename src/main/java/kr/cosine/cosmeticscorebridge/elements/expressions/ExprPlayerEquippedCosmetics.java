package kr.cosine.cosmeticscorebridge.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kr.cosine.cosmeticscorebridge.CosmeticsCoreBridge;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;
import kr.cosine.cosmeticscorebridge.service.CosmeticsService;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class ExprPlayerEquippedCosmetics extends SimpleExpression<Cosmetics> {

    static {
        Skript.registerExpression(
            ExprPlayerEquippedCosmetics.class,
            Cosmetics.class,
            ExpressionType.SIMPLE,
            "equipped cosmetics of %player%", "%player%'s equipped cosmetics"
        );
    }

    private final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private final CosmeticsService cosmeticsService = plugin.getCosmeticsService();

    private Expression<Player> expressionPlayer;

    @Override
    protected Cosmetics[] get(Event event) {
        Player player = expressionPlayer.getSingle(event);
        if (player != null) {
            return cosmeticsService.getEquippedCosmetics(player).toArray(Cosmetics[]::new);
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Cosmetics> getReturnType() {
        return Cosmetics.class;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return expressionPlayer.toString(event, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        expressionPlayer = (Expression<Player>) exprs[0];
        return true;
    }
}