package kr.cosine.cosmeticscorebridge.service;

import kr.cosine.cosmeticscorebridge.CosmeticsCoreBridge;
import org.bukkit.Server;

public class SchedulerService {

    private static final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private static final Server server = plugin.getServer();

    public static void async(Runnable runnable) {
        server.getScheduler().runTaskAsynchronously(plugin, runnable);
    }
}
