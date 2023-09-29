package kr.cosine.cosmeticscorebridge;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import kr.cosine.cosmeticscorebridge.service.CosmeticsService;
import kr.cosine.cosmeticscorebridge.service.PermissionService;
import kr.cosine.cosmeticscorebridge.service.SchedulerService;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class CosmeticsCoreBridge extends JavaPlugin {

    private static CosmeticsCoreBridge plugin;

    private SkriptAddon addon;

    private CosmeticsService cosmeticsService;

    private PermissionService permissionService;

    private SchedulerService schedulerService;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("kr.cosine.cosmeticscorebridge", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }
        schedulerService = new SchedulerService();
        permissionService = new PermissionService();
        cosmeticsService = new CosmeticsService();
    }

    @Override
    public void onDisable() {

    }

    public static CosmeticsCoreBridge getInstance() {
        return plugin;
    }

    public CosmeticsService getCosmeticsService() {
        return cosmeticsService;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public SchedulerService getSchedulerService() {
        return schedulerService;
    }
}
