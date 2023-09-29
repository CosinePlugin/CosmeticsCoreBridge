package kr.cosine.cosmeticscorebridge.service;

import dev.lone.cosmeticscore.api.temporary.CosmeticAccessor;
import dev.lone.cosmeticscore.api.temporary.CosmeticsCoreApi;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class CosmeticsService {

    private CosmeticsCoreApi cosmeticsCoreApi = new CosmeticsCoreApi();

    public List<Cosmetics> getCosmetics() {
        List<String> cosmeticsKeys = (List<String>) CosmeticsCoreApi.getCosmeticsKeysCopy();
        Player player = (Player) Bukkit.getOnlinePlayers().toArray()[0];
        return getCosmeticsFromKeys(cosmeticsKeys, player);
    }

    public List<Cosmetics> getEquippedCosmetics(Player player) {
        List<CosmeticAccessor> accessors = cosmeticsCoreApi.getEquippedCosmeticsAccessors(player);
        return accessors.stream().map(this::getCosmeticsByCosmeticAccessor).toList();
    }

    public List<Cosmetics> getCosmeticsFromPermissions(List<String> permissions, Player player) {
        List<String> cosmeticsKeys = getCosmeticsKeysFromPermissions(permissions);
        return getCosmeticsFromKeys(cosmeticsKeys, player);
    }

    public List<String> getCosmeticsKeysFromPermissions(List<String> permissions) {
        return permissions.stream().map(permission -> {
            String[] split = permission.split("\\.");
            return split[split.length - 1];
        }).toList();
    }

    public List<Cosmetics> getCosmeticsFromKeys(List<String> keys, Player player) {
        return keys.stream().map(key -> {
            CosmeticAccessor accessor = CosmeticsCoreApi.newCosmeticAccessor(key, player);
            return getCosmeticsByCosmeticAccessor(accessor);
        }).toList();
    }

    public Cosmetics getCosmeticsByCosmeticAccessor(CosmeticAccessor accessor) {
        return new Cosmetics()
            .setKey(accessor.getKey())
            .setDisplayName(accessor.getDisplayName())
            .setItemStack(accessor.getGuiModelItem());
    }

    public void equipCosmetics(String key, Player player) {
        CosmeticAccessor accessor = CosmeticsCoreApi.newCosmeticAccessor(key, player);
        accessor.equip();
    }

    public void unequipCosmetics(String key, Player player) {
        CosmeticAccessor accessor = CosmeticsCoreApi.newCosmeticAccessor(key, player);
        accessor.unequip();
    }
}
