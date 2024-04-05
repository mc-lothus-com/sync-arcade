package com.lothus.sync.stats.player.games.skywars;

import com.lothus.core.Core;
import com.lothus.core.games.room.RoomType;
import com.lothus.core.player.LothPlayer;
import com.lothus.sync.stats.games.addons.ability.Ability;
import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import com.lothus.sync.stats.games.addons.kit.Kit;
import com.lothus.sync.stats.games.addons.slaughter.Slaughter;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.level.LevelColor;
import com.lothus.sync.stats.player.maps.FavoriteMap;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class SkyPlayer {

    private UUID uniqueId;

    private List<FavoriteMap> favorites;

    private int level;
    private int xp;
    private int coins;

    private int totalKills;
    private int totalWins;

    private String deathCry;
    private String slaughter;
    private String cage;

    public SkyPlayer(UUID uniqueId) {
        this.uniqueId = uniqueId;

        coins = 0;

        favorites = new ArrayList<>();

        level = 0;
        xp = 0;

        totalKills = 0;
        totalWins = 0;

        deathCry = "None";
        slaughter = "None";
        cage = "None";
    }

    public LevelColor getLevelColor() {
        return LevelColor.getLevelColor(level);
    }

    public List<Kit> getAvailableKits(RoomType type) {
        List<Kit> ks = new ArrayList<>();
        LothPlayer l = Core.getPlayerController().get(uniqueId);
        for (Kit kit : Platform.getSkyPlatform().getKitController().getKits()) {
            if (l.getGroup().containsPermission((type == RoomType.SOLO ? kit.getSoloPermission() : kit.getTeamPermission()))) {
                ks.add(kit);
            }
        }
        return ks;
    }


    public List<Ability> getAvailableAbility(RoomType type) {
        List<Ability> ks = new ArrayList<>();
        LothPlayer l = Core.getPlayerController().get(uniqueId);
        for (Ability kit : Platform.getSkyPlatform().getAbilityController().getAbility()) {
            if (l.getGroup().containsPermission((type == RoomType.SOLO ? kit.getSoloPermission() : kit.getTeamPermission()))) {
                ks.add(kit);
            }
        }
        return ks;
    }

    public FavoriteMap getFavoriteMap(String mapName) {
        for (FavoriteMap favoriteMap : favorites) {
            if (favoriteMap.getDisplay().equalsIgnoreCase(mapName)) {
                return favoriteMap;
            }
        }
        return null;
    }

    public void addFavoriteMap(FavoriteMap favoriteMap) {
        favorites.add(favoriteMap);
    }

    public boolean hasDeathCry(DeathCry deathCry) {
        LothPlayer lothPlayer = Core.getPlayerController().get(uniqueId);
        return lothPlayer.getGroup().containsPermission(deathCry.getPermission());
    }
    public boolean hasSlaughter(Slaughter slaughter) {
        LothPlayer lothPlayer = Core.getPlayerController().get(uniqueId);
        return lothPlayer.getGroup().containsPermission(slaughter.getPermission());
    }
    public void removeFavoriteMap(String mapName) {
        FavoriteMap favoriteMap = getFavoriteMap(mapName);
        if (favoriteMap != null) {
            favorites.remove(favoriteMap);
        }
    }
}
