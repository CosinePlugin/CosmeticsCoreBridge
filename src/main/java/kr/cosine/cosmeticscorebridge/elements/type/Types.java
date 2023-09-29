package kr.cosine.cosmeticscorebridge.elements.type;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.registrations.Classes;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(Cosmetics.class, "cosmetics")
            .defaultExpression(new EventValueExpression<>(Cosmetics.class))
            .user("cosmetics")
            .parser(new Parser<>() {

                @Override
                public String toString(Cosmetics cosmetics, int flags) {
                    return toVariableNameString(cosmetics);
                }

                @Override
                public String toVariableNameString(Cosmetics cosmetics) {
                    return "cosmetics: " + cosmetics.getKey() + ", " + cosmetics.getDisplayName();
                }
            })
        );
    }
}
