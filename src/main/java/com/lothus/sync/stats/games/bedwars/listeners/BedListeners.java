package com.lothus.sync.stats.games.bedwars.listeners;

import com.lothus.sync.stats.platform.Platform;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BedListeners implements Listener {

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        Platform.getBedPlatform().getBedPlayerController().load(event.getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Platform.getBedPlatform().getBedPlayerController().unload(event.getPlayer().getUniqueId());
    }
}
