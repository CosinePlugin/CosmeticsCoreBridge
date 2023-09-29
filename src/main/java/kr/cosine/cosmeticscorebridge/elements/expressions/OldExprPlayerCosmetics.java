package kr.cosine.cosmeticscorebridge.elements.expressions;

/*
public class ExprPlayerCosmetics extends SimpleExpression<String> {

    static {
        Skript.registerExpression(
            ExprPlayerCosmetics.class,
            String.class,
            ExpressionType.SIMPLE,
            "cosmetics of %player%", "%player%'s cosmetics"
        );
    }

    private Expression<Player> expressionPlayer;

    @Override
    protected String[] get(Event event) {
        Player player = expressionPlayer.getSingle(event);
        if (player != null) {
            var keys = PermissionService.getPermissions(player, false);
            return keys.toArray(String[]::new);
        }
        return null;
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
                    String input = (String) delta[0];
                    PermissionService.addPermission(player, input);
                }
                case REMOVE -> {
                    String input = (String) delta[0];
                    PermissionService.removePermission(player, input);
                }
                case DELETE, RESET -> PermissionService.removeAllPermission(player);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD ||
            mode == Changer.ChangeMode.REMOVE ||
            mode == Changer.ChangeMode.DELETE ||
            mode == Changer.ChangeMode.RESET
        ) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}
*/
