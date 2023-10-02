package kr.cosine.cosmeticscorebridge.enums;

public enum CosmeticsPermission {
    WEAR("cosmeticscore.user.cosmetics.wear"),
    SEE_IN_GUI("cosmeticscore.user.cosmetics.seeingui");

    public final String permission;

    CosmeticsPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission(String cosmeticName) {
        return permission + "." + cosmeticName;
    }

    public String getWithPoint() {
        return permission + ".";
    }
}
