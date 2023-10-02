package kr.cosine.cosmeticscorebridge.service;

import dev.lone.cosmeticscore.api.temporary.CosmeticAccessor;
import dev.lone.cosmeticscore.api.temporary.CosmeticsCoreApi;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;
import kr.cosine.cosmeticscorebridge.enums.CosmeticsPermission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CosmeticsService {

    private final CosmeticsCoreApi cosmeticsCoreApi = new CosmeticsCoreApi();

    public boolean isCosmetics(String key) {
        return getCosmeticsKeys().contains(key);
    }

    public List<Cosmetics> getCosmetics() {
        List<String> cosmeticsKeys = getCosmeticsKeys();
        Player player = getAnyonePlayer();
        return getCosmeticsByKeys(cosmeticsKeys, player);
    }

    public List<Cosmetics> getEquippedCosmetics(Player player) {
        List<CosmeticAccessor> accessors = cosmeticsCoreApi.getEquippedCosmeticsAccessors(player);
        return accessors.stream().map(this::getCosmeticsByCosmeticAccessor).toList();
    }

    public List<Cosmetics> getCosmeticsByPermissions(List<String> permissions, Player player) {
        List<String> cosmeticsKeys = getCosmeticsKeysByPermissions(permissions);
        return getCosmeticsByKeys(cosmeticsKeys, player);
    }

    public List<String> getCosmeticsKeysByPermissions(List<String> permissions) {
        return permissions.stream().map(permission -> permission
            .replace(CosmeticsPermission.WEAR.getWithPoint(), "")
            .replace(CosmeticsPermission.SEE_IN_GUI.getWithPoint(), "")
        ).toList();
    }

    public Cosmetics getCosmeticsByKey(String key, Player player) {
        CosmeticAccessor accessor = getCosmeticsAccessor(key, player);
        return getCosmeticsByCosmeticAccessor(accessor);
    }

    public Cosmetics getCosmeticsByKey(String key) {
        Player player = getAnyonePlayer();
        return getCosmeticsByKey(key, player);
    }

    public List<Cosmetics> getCosmeticsByKeys(List<String> keys, Player player) {
        List<Cosmetics> cosmeticsList = new ArrayList<>();
        for (String key : keys) {
            Cosmetics cosmetics = getCosmeticsByKey(key, player);
            if (cosmetics == null) {
                return new ArrayList<>();
            } else {
                cosmeticsList.add(cosmetics);
            }
        }
        return cosmeticsList;
    }

    public Cosmetics getCosmeticsByCosmeticAccessor(CosmeticAccessor accessor) {
        try {
            return new Cosmetics()
                .setKey(accessor.getKey())
                .setDisplayName(accessor.getDisplayName())
                .setItemStack(accessor.getGuiModelItem());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void equipCosmetics(String key, Player player) {
        CosmeticAccessor accessor = getCosmeticsAccessor(key, player);
        accessor.equip();
    }

    public void unequipCosmetics(String key, Player player) {
        CosmeticAccessor accessor = getCosmeticsAccessor(key, player);
        accessor.unequip();
    }

    public Cosmetics getEmptyCosmetics() {
        Cosmetics cosmetics = new Cosmetics();
        cosmetics.isNotNull = false;
        return cosmetics;
    }

    private List<String> getCosmeticsKeys() {
        return (List<String>) CosmeticsCoreApi.getCosmeticsKeysCopy();
    }

    private CosmeticAccessor getCosmeticsAccessor(String key, Player player) {
        return CosmeticsCoreApi.newCosmeticAccessor(key, player);
    }
    
    private Player getAnyonePlayer() {
        return (Player) Bukkit.getOnlinePlayers().toArray()[0];
    }
}
