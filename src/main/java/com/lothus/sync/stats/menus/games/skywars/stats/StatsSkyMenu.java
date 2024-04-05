package com.lothus.sync.stats.menus.games.skywars.stats;

import com.henryfabio.minecraft.inventoryapi.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.inventory.impl.simple.SimpleInventory;
import com.henryfabio.minecraft.inventoryapi.item.InventoryItem;
import com.henryfabio.minecraft.inventoryapi.viewer.Viewer;
import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.skywars.stats.SkyStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class StatsSkyMenu  extends SimpleInventory {

    public StatsSkyMenu() {
        super(
                "skywars-stats",
                "Estatísticas - Sky Wars",
                9*3
        );
    }

    @Override
    protected void configureInventory(Viewer viewer, InventoryEditor editor) {
        Player player = viewer.getPlayer();


        SkyStats solo = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_SOLO, player.getUniqueId());
        SkyStats team = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_TEAM, player.getUniqueId());

        editor.setItem(12, InventoryItem.of(new ItemCreator(Material.GRASS, "§eSky Wars Solo")
                .setLore(
                        "",
                        "§fVitórias: §6" + solo.getWins(),
                        "§fDerrotas: §6" + solo.getLoses(),
                        "",
                        "§fKills: §6" + solo.getKills(),
                        "§fMortes: §6" + solo.getDeaths(),
                        "",
                        "§fWinstreak: §6" + solo.getCurrentWinstreak(),
                        "§fM. Winstreak: §6" + solo.getBestWinstreak()
                ).build())
                .defaultCallback(event -> {
                    event.setCancelled(true);
                }));
        editor.setItem(14, InventoryItem.of(new ItemCreator(Material.GRASS, "§eSky Wars Duplas")
                .setLore(
                        "",
                        "§fVitórias: §6" + team.getWins(),
                        "§fDerrotas: §6" + team.getLoses(),
                        "",
                        "§fKills: §6" + team.getKills(),
                        "§fMortes: §6" + team.getDeaths(),
                        "",
                        "§fWinstreak: §6" + team.getCurrentWinstreak(),
                        "§fM. Winstreak: §6" + team.getBestWinstreak()
                ).build())
                .defaultCallback(event -> {
                    event.setCancelled(true);
                }));
    }
}