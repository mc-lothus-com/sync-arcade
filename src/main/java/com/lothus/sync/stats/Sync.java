package com.lothus.sync.stats;

import com.lothus.core.Core;
import com.lothus.core.api.loaders.ListenerLoader;
import com.lothus.core.games.type.GameType;
import com.lothus.core.servers.type.ServerType;
import com.lothus.sync.stats.controller.bedwars.menus.BedWarsMenus;
import com.lothus.sync.stats.controller.skywars.menus.SkyWarsMenus;
import com.lothus.sync.stats.data.player.DataPlayer;
import com.lothus.sync.stats.data.stats.DataStats;
import com.lothus.sync.stats.games.addons.ability.loader.AbilityLoader;
import com.lothus.sync.stats.games.addons.deathcries.loader.DeathLoader;
import com.lothus.sync.stats.games.addons.kit.loader.KitLoader;
import com.lothus.sync.stats.games.addons.slaughter.loader.SlaughterLoader;
import com.lothus.sync.stats.platform.Platform;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public class Sync {

    @Getter @Setter
    private static JavaPlugin plugin;

    @Getter @Setter
    private static GameType type;

    public static void loadLobby(JavaPlugin plugin) {
        setPlugin(plugin);

        Platform.setDataStats(new DataStats());
        Platform.setDataPlayer(new DataPlayer());

        KitLoader.loadKit(plugin, "com.lothus.sync.stats.games.addons.kit.register");
        AbilityLoader.loadAbility(plugin, "com.lothus.sync.stats.games.addons.ability.register");
        DeathLoader.loadDeaths(plugin, "com.lothus.sync.stats.games.addons.deathcries.register");
        SlaughterLoader.loadSlaughter(plugin, "com.lothus.sync.stats.games.addons.slaughter.register");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.skywars.listeners");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.bedwars.listeners");

        if (Core.getServerInfo().getType().equals(ServerType.LOBBY_SKYWARS)) {
            type = GameType.SKY_WARS;
            Platform.getMenuController().setSkyWarsMenus(new SkyWarsMenus());
            ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.skywars");
        } else if (Core.getServerInfo().getType().equals(ServerType.LOBBY_BEDWARS)) {
            type = GameType.BED_WARS;
            Platform.getMenuController().setBedWarsMenus(new BedWarsMenus());
            ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.bedwars");
        }
    }

    public Sync(JavaPlugin plugin) {
        setPlugin(plugin);

        Platform.setDataStats(new DataStats());
        Platform.setDataPlayer(new DataPlayer());

        Platform.getMenuController().setSkyWarsMenus(new SkyWarsMenus());

        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.skywars.listeners");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.bedwars.listeners");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.skywars");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.bedwars");
    }

    public Sync(JavaPlugin plugin, GameType gameType) {
        setPlugin(plugin);
        this.type = gameType;

        Platform.setDataStats(new DataStats());
        Platform.setDataPlayer(new DataPlayer());

        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.skywars.listeners");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.bedwars.listeners");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.skywars");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.bedwars");

        Platform.getMenuController().setSkyWarsMenus(new SkyWarsMenus());

        DeathLoader.loadDeaths(plugin, "com.lothus.sync.stats.games.addons.deathcries.register");
        SlaughterLoader.loadSlaughter(plugin, "com.lothus.sync.stats.games.addons.slaughter.register");

        if (gameType == GameType.SKY_WARS) {
            KitLoader.loadKit(plugin, "com.lothus.sync.stats.games.addons.kit.register");
            AbilityLoader.loadAbility(plugin, "com.lothus.sync.stats.games.addons.ability.register");
        }
    }

    public Sync(JavaPlugin plugin, GameType gameType, boolean  b) {
        setPlugin(plugin);
        this.type = gameType;

        Platform.setDataStats(new DataStats());
        Platform.setDataPlayer(new DataPlayer());

        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.skywars.listeners");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.bedwars.listeners");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.skywars");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.bedwars");

        Platform.getMenuController().setSkyWarsMenus(new SkyWarsMenus());

        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.skywars");
        ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.games.skywars.listeners");

        if (gameType == GameType.SKY_WARS) {
            if (b) {
                ListenerLoader.loadListeners(plugin, "com.lothus.sync.stats.menus.games.skywars");
                KitLoader.loadKit(plugin, "com.lothus.sync.stats.games.addons.kit.register");
                AbilityLoader.loadAbility(plugin, "com.lothus.sync.stats.games.addons.ability.register");
                DeathLoader.loadDeaths(plugin, "com.lothus.sync.stats.games.addons.deathcries.register");
                SlaughterLoader.loadSlaughter(plugin, "com.lothus.sync.stats.games.addons.slaughter.register");
            }
        }
    }
}
