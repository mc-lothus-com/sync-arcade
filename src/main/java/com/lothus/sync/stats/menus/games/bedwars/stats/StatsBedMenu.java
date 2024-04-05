package com.lothus.sync.stats.menus.games.bedwars.stats;

import com.henryfabio.minecraft.inventoryapi.editor.InventoryEditor;
import com.henryfabio.minecraft.inventoryapi.inventory.impl.simple.SimpleInventory;
import com.henryfabio.minecraft.inventoryapi.item.InventoryItem;
import com.henryfabio.minecraft.inventoryapi.viewer.Viewer;
import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.bedwars.BedPlayer;
import com.lothus.sync.stats.player.games.bedwars.league.BedWarsLeague;
import com.lothus.sync.stats.player.games.bedwars.stats.BedStats;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class StatsBedMenu extends SimpleInventory {

    public StatsBedMenu() {
        super(
                "bedwars-stats",
                "Estatísticas - Bed Wars",
                9*3
        );
    }

    @Override
    protected void configureInventory(Viewer viewer, InventoryEditor editor) {
        Player player = viewer.getPlayer();

        BedPlayer bedPlayer = Platform.getBedPlatform().getBedPlayerController().getAccount(player.getUniqueId());
        BedStats solo = Platform.getBedPlatform().getBedPlayerController().get(DataType.BED_WARS_SOLO, player.getUniqueId());
        BedStats team = Platform.getBedPlatform().getBedPlayerController().get(DataType.BED_WARS_TEAM, player.getUniqueId());
        BedStats trios = Platform.getBedPlatform().getBedPlayerController().get(DataType.BED_WARS_TRIO, player.getUniqueId());
        BedStats quartetos = Platform.getBedPlatform().getBedPlayerController().get(DataType.BED_WARS_QUARTETO, player.getUniqueId());
        BedStats ranked = Platform.getBedPlatform().getBedPlayerController().get(DataType.BED_WARS_RANKED, player.getUniqueId());

        editor.setItem(4, InventoryItem.of(new ItemCreator(Material.PAPER, "§eGeral")
                .setLore(
                        "",
                        "§fSeu nível: " + bedPlayer.getLevelColor().getColor() + "[" + bedPlayer.getLevel() + "✧]",
                        "§fXP: §7(" + bedPlayer.getXp() + "/500)",
                        "",
                        "§fLiga: " + BedWarsLeague.getTag(bedPlayer.getLeague()),
                        "§fPontos: §7" + bedPlayer.getPoints() + "/" + BedWarsLeague.nextLevel(bedPlayer.getLeague()).getPoints(),
                        "",
                        "§fTotal de vitórias: §7" + (solo.getWins() + team.getWins() + trios.getWins() + quartetos.getWins() + ranked.getWins()),
                        "§fTotal de vítimas: §7" + (solo.getWins() + team.getWins() + trios.getWins() + quartetos.getWins() + ranked.getWins()),
                        "",
                        "§fCoins: §7" + bedPlayer.getCoins(),
                        ""
                ).build()));

        editor.setItem(10, InventoryItem.of(new ItemCreator(Material.BED, "§eBed Wars Solo")
                .setLore(
                        "",
                        "§fC. Destruidas: §6" + solo.getDestroyedBeds(),
                        "§fC. Perdidas: §6" + solo.getLossBeds(),
                        "",
                        "§fVitórias: §6" + solo.getWins(),
                        "§fDerrotas: §6" + solo.getLoses(),
                        "",
                        "§fKills: §6" + solo.getKills(),
                        "§fMortes: §6" + solo.getDeaths(),
                        "§fKills Finais: §6" + solo.getFinalKills(),
                        "§fMortes Finais: §6" + solo.getFinalDeaths(),
                        "",
                        "§fWinstreak: §6" + solo.getCurrentWinstreak(),
                        "§fM. Winstreak: §6" + solo.getBestWinstreak()
                ).build())
                .defaultCallback(event -> {
                    event.setCancelled(true);
                }));
        editor.setItem(13, InventoryItem.of(new ItemCreator(Material.NETHER_STAR, "§eBed Wars Ranqueado")
                .setLore(
                        "",
                        "§fLiga: " + BedWarsLeague.getTag(bedPlayer.getLeague()),
                        "§fPontos: §7" + bedPlayer.getPoints(),
                        "",
                        "§fC. Destruidas: §6" + ranked.getDestroyedBeds(),
                        "§fC. Perdidas: §6" + ranked.getLossBeds(),
                        "",
                        "§fVitórias: §6" + ranked.getWins(),
                        "§fDerrotas: §6" + ranked.getLoses(),
                        "",
                        "§fKills: §6" + ranked.getKills(),
                        "§fMortes: §6" + ranked.getDeaths(),
                        "§fKills Finais: §6" + ranked.getFinalKills(),
                        "§fMortes Finais: §6" + ranked.getFinalDeaths(),
                        "",
                        "§fWinstreak: §6" + ranked.getCurrentWinstreak(),
                        "§fM. Winstreak: §6" + ranked.getBestWinstreak()
                ).setAmount(1).build())
                .defaultCallback(event -> {
                    event.setCancelled(true);
                }));
        editor.setItem(11, InventoryItem.of(new ItemCreator(Material.BED, "§eBed Wars Duplas")
                .setLore(
                        "",
                        "§fC. Destruidas: §6" + team.getDestroyedBeds(),
                        "§fC. Perdidas: §6" + team.getLossBeds(),
                        "",
                        "§fVitórias: §6" + team.getWins(),
                        "§fDerrotas: §6" + team.getLoses(),
                        "",
                        "§fKills: §6" + team.getKills(),
                        "§fMortes: §6" + team.getDeaths(),
                        "§fKills Finais: §6" + team.getFinalKills(),
                        "§fMortes Finais: §6" + team.getFinalDeaths(),
                        "",
                        "§fWinstreak: §6" + team.getCurrentWinstreak(),
                        "§fM. Winstreak: §6" + team.getBestWinstreak()
                ).setAmount(2).build())
                .defaultCallback(event -> {
                    event.setCancelled(true);
                }));
        editor.setItem(15, InventoryItem.of(new ItemCreator(Material.BED, "§eBed Wars Trios")
                .setLore(
                        "",
                        "§fC. Destruidas: §6" + trios.getDestroyedBeds(),
                        "§fC. Perdidas: §6" + trios.getLossBeds(),
                        "",
                        "§fVitórias: §6" + trios.getWins(),
                        "§fDerrotas: §6" + trios.getLoses(),
                        "",
                        "§fKills: §6" + trios.getKills(),
                        "§fMortes: §6" + trios.getDeaths(),
                        "§fKills Finais: §6" + trios.getFinalKills(),
                        "§fMortes Finais: §6" + trios.getFinalDeaths(),
                        "",
                        "§fWinstreak: §6" + trios.getCurrentWinstreak(),
                        "§fM. Winstreak: §6" + trios.getBestWinstreak()
                ).setAmount(3).build())
                .defaultCallback(event -> {
                    event.setCancelled(true);
                }));
        editor.setItem(16, InventoryItem.of(new ItemCreator(Material.BED, "§eBed Wars Quartetos")
                .setLore(
                        "",
                        "§fC. Destruidas: §6" + quartetos.getDestroyedBeds(),
                        "§fC. Perdidas: §6" + quartetos.getLossBeds(),
                        "",
                        "§fVitórias: §6" + quartetos.getWins(),
                        "§fDerrotas: §6" + quartetos.getLoses(),
                        "",
                        "§fKills: §6" + quartetos.getKills(),
                        "§fMortes: §6" + quartetos.getDeaths(),
                        "§fKills Finais: §6" + quartetos.getFinalKills(),
                        "§fMortes Finais: §6" + quartetos.getFinalDeaths(),
                        "",
                        "§fWinstreak: §6" + quartetos.getCurrentWinstreak(),
                        "§fM. Winstreak: §6" + quartetos.getBestWinstreak()
                ).setAmount(4).build())
                .defaultCallback(event -> {
                    event.setCancelled(true);
                }));
    }
}
