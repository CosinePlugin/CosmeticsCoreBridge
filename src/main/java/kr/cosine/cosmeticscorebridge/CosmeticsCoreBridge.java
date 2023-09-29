package kr.cosine.cosmeticscorebridge;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class CosmeticsCoreBridge extends JavaPlugin {

    private static CosmeticsCoreBridge plugin;

    private SkriptAddon addon;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        registerSkript();
    }

    private void registerSkript() {
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("kr.cosine.cosmeticscorebridge", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {

    }

    public static CosmeticsCoreBridge getInstance() {
        return plugin;
    }
}
