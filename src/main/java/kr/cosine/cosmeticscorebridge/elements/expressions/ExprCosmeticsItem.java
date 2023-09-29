package kr.cosine.cosmeticscorebridge.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;
import org.bukkit.inventory.ItemStack;

public class ExprCosmeticsItem extends SimplePropertyExpression<Cosmetics, ItemStack> {

    static {
        register(
            ExprCosmeticsItem.class,
            ItemStack.class,
            "model",
            "cosmetic"
        );
    }

    @Override
    protected String getPropertyName() {
        return "model";
    }

    @Override
    public ItemStack convert(Cosmetics cosmetics) {
        return cosmetics.getItemStack().clone();
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }
}
