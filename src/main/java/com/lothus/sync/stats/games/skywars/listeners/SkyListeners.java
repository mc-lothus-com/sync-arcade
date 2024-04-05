package com.lothus.sync.stats.games.skywars.listeners;

import com.lothus.sync.stats.Sync;
import com.lothus.sync.stats.platform.Platform;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SkyListeners implements Listener {

    @EventHandler
    public void load(AsyncPlayerPreLoginEvent event) {
        Platform.getSkyPlatform().getSkyPlayerController().load(event.getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Platform.getSkyPlatform().getSkyPlayerController().unload(event.getPlayer().getUniqueId());
            }
        }.runTaskLater(Sync.getPlugin(), 3L);
    }
}
