package com.lothus.sync.stats.player.games.bedwars;

import com.lothus.core.Core;
import com.lothus.core.player.LothPlayer;
import com.lothus.core.utils.bukkit.BukkitSerialization;
import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import com.lothus.sync.stats.games.addons.slaughter.Slaughter;
import com.lothus.sync.stats.player.games.bedwars.league.BedWarsLeague;
import com.lothus.sync.stats.player.level.LevelColor;
import com.lothus.sync.stats.player.maps.FavoriteMap;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class BedPlayer {

    private UUID uniqueId;

    private int level;
    private int xp;

    private int totalKills;
    private int totalWins;

    private int coins;

    private int leagueId;
    private int points;

    private List<FavoriteMap> favoriteMaps;
    private List<String> quickbuys;

    private String deathCry;
    private String slaughter;
    private String cage;

    public BedPlayer(UUID uniqueId) {
        this.uniqueId = uniqueId;

        level = 0;
        xp = 0;

        coins = 0;

        totalWins = 0;
        totalKills = 0;

        leagueId = 1;
        points = 0;

        favoriteMaps = new ArrayList<>();
        quickbuys = new ArrayList<>();

        deathCry = "None";
        slaughter = "None";
        cage = "None";
    }

    public BedWarsLeague getLeague() {
        return BedWarsLeague.getLeague(leagueId);
    }

    public void addQuickBuy(ItemStack itemStack) {
        if (quickbuys == null) {
            quickbuys = new ArrayList<>();
        }
        quickbuys.add(BukkitSerialization.itemStackToBase64(itemStack));
    }

    public List<ItemStack> getQuickBuy() {
        List<ItemStack> itemStacks = new ArrayList<>();

        if (quickbuys != null) {
            quickbuys.forEach(s -> {
                try {
                    itemStacks.add(BukkitSerialization.itemStackFromBase64(s));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return itemStacks;
    }

    public FavoriteMap getFavoriteMap(String mapName) {
        for (FavoriteMap favoriteMap : favoriteMaps) {
            if (favoriteMap.getDisplay().equalsIgnoreCase(mapName)) {
                return favoriteMap;
            }
        }
        return null;
    }

    public void addFavoriteMap(FavoriteMap favoriteMap) {
        favoriteMaps.add(favoriteMap);
    }

    public void removeFavoriteMap(String mapName) {
        FavoriteMap favoriteMap = getFavoriteMap(mapName);
        if (favoriteMap != null) {
            favoriteMaps.remove(favoriteMap);
        }
    }

    public boolean hasDeathCry(DeathCry deathCry) {
        LothPlayer lothPlayer = Core.getPlayerController().get(uniqueId);
        return lothPlayer.getGroup().containsPermission(deathCry.getPermission());
    }
    public boolean hasSlaughter(Slaughter slaughter) {
        LothPlayer lothPlayer = Core.getPlayerController().get(uniqueId);
        return lothPlayer.getGroup().containsPermission(slaughter.getPermission());
    }

    public LevelColor getLevelColor() {
        return LevelColor.getLevelColor(level);
    }
}
