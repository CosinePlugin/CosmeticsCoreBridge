package kr.cosine.cosmeticscorebridge.service;

import dev.lone.cosmeticscore.api.temporary.CosmeticAccessor;
import dev.lone.cosmeticscore.api.temporary.CosmeticsCoreApi;
import kr.cosine.cosmeticscorebridge.data.Cosmetics;
import org.bukkit.entity.Player;

import java.util.List;

public class CosmeticsService {

    public static List<String> getCosmeticsKeys(List<String> permissions) {
        return permissions.stream().map(permission -> {
            String[] split = permission.split("\\.");
            return split[split.length - 1];
        }).toList();
    }

    public static List<Cosmetics> getCosmeticsFromKeys(List<String> keys, Player player) {
        return keys.stream().map(key -> {
            CosmeticAccessor accessor = CosmeticsCoreApi.newCosmeticAccessor(key, player);
            return getByCosmeticAccessor(accessor);
        }).toList();
    }

    private static Cosmetics getByCosmeticAccessor(CosmeticAccessor accessor) {
        Cosmetics cosmetics = new Cosmetics();
        cosmetics.setKey(accessor.getKey());
        cosmetics.setDisplayName(accessor.getDisplayName());
        return cosmetics;
    }
}
