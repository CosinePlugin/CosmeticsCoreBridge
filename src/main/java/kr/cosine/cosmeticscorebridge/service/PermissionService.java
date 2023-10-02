package kr.cosine.cosmeticscorebridge.service;

import kr.cosine.cosmeticscorebridge.enums.CosmeticsPermission;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.data.NodeMap;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.ScopedNode;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class PermissionService {

    private final UserManager manager = LuckPermsProvider.get().getUserManager();

    public List<String> getPermissions(Player player, boolean isNewLoad, boolean onlyWear) {
        UUID playerUniqueId = player.getUniqueId();
        User user;
        if (isNewLoad) {
            user = manager.loadUser(playerUniqueId).join();
        } else {
            user = manager.getUser(playerUniqueId);
        }
        return user.data().toCollection().stream()
            .map(Node::getKey)
            .filter(key -> {
                if (onlyWear) {
                    return key.contains(CosmeticsPermission.WEAR.getWithPoint());
                } else {
                    return key.contains(CosmeticsPermission.WEAR.getWithPoint()) ||
                        key.contains(CosmeticsPermission.SEE_IN_GUI.getWithPoint());
                }
            }).toList();
    }

    public void addPermission(Player player, String cosmeticName) {
        modifyPermission(player.getUniqueId(), cosmeticName, NodeMap::add);
    }

    public void removePermission(Player player, String cosmeticName) {
        modifyPermission(player.getUniqueId(), cosmeticName, NodeMap::remove);
    }

    public void removeAllPermission(Player player) {
        manager.modifyUser(player.getUniqueId(), user -> {
            NodeMap userData = user.data();
            getPermissions(player, false, false).forEach(permission -> {
                ScopedNode<?, ?> node = getNode(permission);
                userData.remove(node);
            });
        });
    }

    public void modifyPermission(UUID uniqueId, String cosmeticName, BiConsumer<NodeMap, ScopedNode<?, ?>> actionFunction) {
        manager.modifyUser(uniqueId, user -> {
            for (CosmeticsPermission cosmeticsPermission : CosmeticsPermission.values()) {
                ScopedNode<?, ?> node = getNode(cosmeticsPermission.getPermission(cosmeticName));
                actionFunction.accept(user.data(), node);
            }
        });
    }

    private ScopedNode<?, ?> getNode(String permission) {
        return Node.builder(permission).build();
    }
}
