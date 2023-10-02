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

public class ExprCosmeticsByKey extends SimpleExpression<Cosmetics> {
    
    static {
        Skript.registerExpression(
            ExprCosmeticsByKey.class,
            Cosmetics.class,
            ExpressionType.SIMPLE,
            "cosmetics by %string%"
        );
    }

    private final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private final CosmeticsService cosmeticsService = plugin.getCosmeticsService();

    private Expression<String> expressionKey;

    @Override
    protected Cosmetics[] get(Event event) {
        String key = expressionKey.getSingle(event);
        if (key != null) {
            Cosmetics cosmetics = cosmeticsService.getCosmeticsByKey(key);
            if (cosmetics != null) {
                return new Cosmetics[]{
                    cosmetics
                };
            }
        }
        return new Cosmetics[] {
            cosmeticsService.getEmptyCosmetics()
        };
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Cosmetics> getReturnType() {
        return Cosmetics.class;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return expressionKey.toString(event, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        expressionKey = (Expression<String>) exprs[0];
        return true;
    }
}
