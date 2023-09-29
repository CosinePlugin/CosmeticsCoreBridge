package kr.cosine.cosmeticscorebridge.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;

public class ExprCosmeticsKey extends SimplePropertyExpression<Cosmetics, String> {

    static {
        register(
            ExprCosmeticsKey.class,
            String.class,
            "key",
            "cosmetic"
        );
    }

    @Override
    protected String getPropertyName() {
        return "key";
    }

    @Override
    public String convert(Cosmetics cosmetics) {
        return cosmetics.getKey();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
