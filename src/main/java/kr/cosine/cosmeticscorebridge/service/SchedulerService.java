package kr.cosine.cosmeticscorebridge.service;

import kr.cosine.cosmeticscorebridge.CosmeticsCoreBridge;
import org.bukkit.Server;

public class SchedulerService {

    private final CosmeticsCoreBridge plugin = CosmeticsCoreBridge.getInstance();
    private final Server server = plugin.getServer();

    public void async(Runnable runnable) {
        server.getScheduler().runTaskAsynchronously(plugin, runnable);
    }
}
