package kr.cosine.cosmeticscorebridge.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;

public class ExprCosmeticsName extends SimplePropertyExpression<Cosmetics, String> {

    static {
        register(
            ExprCosmeticsName.class,
            String.class,
            "display",
            "cosmetic"
        );
    }

    @Override
    protected String getPropertyName() {
        return "display";
    }

    @Override
    public String convert(Cosmetics cosmetics) {
        return cosmetics.getDisplayName();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
