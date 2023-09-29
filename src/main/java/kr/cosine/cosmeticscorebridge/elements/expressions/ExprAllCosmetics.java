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
import org.bukkit.event.Event;

public class ExprAllCosmetics extends SimpleExpression<Cosmetics> {

    static {
        Skript.registerExpression(
            ExprAllCosmetics.class,
            Cosmetics.class,
            ExpressionType.SIMPLE,
            "all cosmetics"
        );
    }

    private final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private final CosmeticsService cosmeticsService = plugin.getCosmeticsService();

    @Override
    protected Cosmetics[] get(Event event) {
        return cosmeticsService.getCosmetics().toArray(Cosmetics[]::new);
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
    public String toString(Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
