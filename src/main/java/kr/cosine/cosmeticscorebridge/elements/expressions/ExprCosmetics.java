package kr.cosine.cosmeticscorebridge.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.lone.cosmeticscore.api.temporary.CosmeticsCoreApi;
import org.bukkit.event.Event;

import java.util.List;

public class ExprCosmetics extends SimpleExpression<String> {

    static {
        Skript.registerExpression(
            ExprCosmetics.class,
            String.class,
            ExpressionType.SIMPLE,
            "all cosmetics"
        );
    }

    @Override
    protected String[] get(Event e) {
        List<?> keys = (List<String>) CosmeticsCoreApi.getCosmeticsKeysCopy();
        return keys.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
