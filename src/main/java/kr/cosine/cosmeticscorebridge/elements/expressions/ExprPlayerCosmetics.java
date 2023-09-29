package kr.cosine.cosmeticscorebridge.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import kr.cosine.cosmeticscorebridge.CosmeticsCoreBridge;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;
import kr.cosine.cosmeticscorebridge.service.CosmeticsService;
import kr.cosine.cosmeticscorebridge.service.PermissionService;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.List;

public class ExprPlayerCosmetics extends SimpleExpression<Cosmetics> {

    static {
        Skript.registerExpression(
            ExprPlayerCosmetics.class,
            Cosmetics.class,
            ExpressionType.SIMPLE,
            "cosmetics of %player%", "%player%'s cosmetics"
        );
    }

    private final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private final PermissionService permissionService = plugin.getPermissionService();
    private final CosmeticsService cosmeticsService = plugin.getCosmeticsService();

    private Expression<Player> expressionPlayer;

    @Override
    protected Cosmetics[] get(Event event) {
        Player player = expressionPlayer.getSingle(event);
        if (player != null) {
            List<String> permissions = permissionService.getPermissions(player, false, true);
            List<Cosmetics> cosmeticsKeys = cosmeticsService.getCosmeticsFromPermissions(permissions, player);
            return cosmeticsKeys.toArray(Cosmetics[]::new);
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

    @Override
    public void change(Event event, Object[] delta, Changer.ChangeMode mode) {
        Player player = expressionPlayer.getSingle(event);
        if (player != null) {
            switch (mode) {
                case ADD -> {
                    String permission = getPermission(delta);
                    permissionService.addPermission(player, permission);
                }
                case REMOVE -> {
                    String permission = getPermission(delta);
                    permissionService.removePermission(player, permission);
                }
                case DELETE, RESET -> permissionService.removeAllPermission(player);
            }
        }
    }

    private String getPermission(Object[] delta) {
        Object input = delta[0];
        String permission;
        if (input instanceof Cosmetics) {
            permission = ((Cosmetics) input).getKey();
        } else {
            permission = (String) input;
        }
        return permission;
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD ||
            mode == Changer.ChangeMode.REMOVE ||
            mode == Changer.ChangeMode.DELETE ||
            mode == Changer.ChangeMode.RESET
        ) {
            return new Class[] {
                String.class,
                Cosmetics.class
            };
        }
        return null;
    }
}