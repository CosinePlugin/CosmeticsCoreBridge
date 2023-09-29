package kr.cosine.cosmeticscorebridge.elements.classes;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;

public class CosmeticsClasses {

    static {
        Classes.registerClass(new ClassInfo<>(Cosmetics.class, "cosmetic")
            .user("cosmetic")
            .name("Cosmetic")
            .since("1.0.0")
            .defaultExpression(new EventValueExpression<>(Cosmetics.class))
            .parser(new Parser<>() {

                @Override
                public boolean canParse(ParseContext context) { return false; }

                @Override
                public String toString(Cosmetics cosmetics, int flags) {
                    return toVariableNameString(cosmetics);
                }

                @Override
                public String toVariableNameString(Cosmetics cosmetics) {
                    return cosmetics.getKey();
                }
            })
        );
    }
}
