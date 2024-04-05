package com.lothus.sync.stats.controller.skywars.player;


import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.skywars.SkyPlayer;
import com.lothus.sync.stats.player.games.skywars.stats.SkyStats;
import com.lothus.sync.stats.player.games.skywars.stats.ranked.SkyRanked;

import java.util.HashMap;
import java.util.UUID;

public class SkyPlayerController {

    private HashMap<UUID, SkyStats> solos = new HashMap<>();
    private HashMap<UUID, SkyStats> teams = new HashMap<>();
    private HashMap<UUID, SkyRanked> ranked = new HashMap<>();

    private HashMap<UUID, SkyPlayer> players = new HashMap<>();

    public void load(UUID uniqueId) {
        for (DataType dataType : DataType.values()) {
            if (!dataType.name().startsWith("SKY_WARS_")) continue;
            getInfos(dataType, uniqueId);
        }
    }

    public void unload(UUID uniqueId) {
        solos.remove(uniqueId);
        teams.remove(uniqueId);
        ranked.remove(uniqueId);
        players.remove(uniqueId);
    }

    public SkyStats get(DataType dataType, UUID uniqueId) {
        if (dataType == DataType.SKY_WARS_SOLO) {
            return solos.get(uniqueId);
        } else if (dataType == DataType.SKY_WARS_TEAM) {
            return teams.get(uniqueId);
        }
        return null;
    }

    public SkyRanked get(UUID uniqueId) {
        return ranked.get(uniqueId);
    }

    public SkyPlayer getAccount(UUID uniqueId) {
        return players.get(uniqueId);
    }


    private void getInfos(DataType dataType, UUID uniqueId) {
        if (dataType == DataType.SKY_WARS_ACCOUNT) {
            SkyPlayer skyPlayer = Platform.getDataPlayer().getSky(dataType, uniqueId);
            if (skyPlayer == null) {
                skyPlayer = new SkyPlayer(uniqueId);
                Platform.getDataPlayer().create(dataType, skyPlayer);
            }
            players.put(uniqueId, skyPlayer);
        } else if (dataType.name().startsWith("SKY_WARS")) {
            SkyStats skyStats = Platform.getDataStats().getSkyStats(dataType, uniqueId);
            if (skyStats == null) {
                skyStats = new SkyStats(uniqueId);
                Platform.getDataStats().create(dataType, skyStats);
            }
            if (dataType == DataType.SKY_WARS_SOLO) {
                solos.put(uniqueId, skyStats);
            } else if (dataType == DataType.SKY_WARS_TEAM) {
                teams.put(uniqueId, skyStats);
            }
        }
    }
}
