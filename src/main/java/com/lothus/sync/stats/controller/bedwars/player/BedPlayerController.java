package com.lothus.sync.stats.controller.bedwars.player;


import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.bedwars.BedPlayer;
import com.lothus.sync.stats.player.games.bedwars.league.BedWarsLeague;
import com.lothus.sync.stats.player.games.bedwars.stats.BedStats;

import java.util.HashMap;
import java.util.UUID;

import static com.lothus.sync.stats.data.type.DataType.*;

public class BedPlayerController {

    private HashMap<UUID, BedPlayer> players = new HashMap<>();

    private HashMap<UUID, BedStats> solos = new HashMap<>();
    private HashMap<UUID, BedStats> teams = new HashMap<>();
    private HashMap<UUID, BedStats> trios = new HashMap<>();
    private HashMap<UUID, BedStats> quartetos = new HashMap<>();
    private HashMap<UUID, BedStats> rankeds = new HashMap<>();

    public void load(UUID uniqueId) {
        for (DataType type : DataType.values()) {
            if (!type.name().startsWith("BED_WARS_"))continue;
            getInfos(type, uniqueId);
        }
    }

    public void unload(UUID uniqueId) {
        solos.remove(uniqueId);
        teams.remove(uniqueId);
        trios.remove(uniqueId);
        quartetos.remove(uniqueId);
        players.remove(uniqueId);
    }

    public BedStats get(DataType dataType, UUID uniqueId) {
        if (dataType == DataType.BED_WARS_SOLO) {
            return solos.get(uniqueId);
        } else if (dataType == BED_WARS_TEAM) {
            return teams.get(uniqueId);
        } else if (dataType == BED_WARS_TRIO) {
            return trios.get(uniqueId);
        } else if (dataType == BED_WARS_QUARTETO) {
            return quartetos.get(uniqueId);
        } else if (dataType == BED_WARS_RANKED) {
            return rankeds.get(uniqueId);
        }
        return null;
    }

    public BedPlayer getAccount(UUID uniqueId) {
        return players.get(uniqueId);
    }

    private void getInfos(DataType dataType, UUID uniqueId) {
        if (dataType == DataType.BED_WARS_ACCOUNT) {
            BedPlayer skyPlayer = Platform.getDataPlayer().getBed(dataType, uniqueId);
            if (skyPlayer == null) {
                skyPlayer = new BedPlayer(uniqueId);
                Platform.getDataPlayer().create(dataType, skyPlayer);
            }
            if (skyPlayer.getLeague() == null) {
                skyPlayer.setLeagueId(BedWarsLeague.INICIANTE.getId());
            }
            players.put(uniqueId, skyPlayer);
        } else if (dataType.name().startsWith("BED_WARS")) {
            BedStats skyStats = Platform.getDataStats().getBedStats(dataType, uniqueId);
            if (skyStats == null) {
                skyStats = new BedStats(uniqueId);
                Platform.getDataStats().create(dataType, skyStats);
            }
            if (dataType == DataType.BED_WARS_SOLO) {
                solos.put(uniqueId, skyStats);
            } else if (dataType == BED_WARS_TEAM) {
                teams.put(uniqueId, skyStats);
            } else if (dataType == BED_WARS_TRIO) {
                trios.put(uniqueId, skyStats);
            } else if (dataType == BED_WARS_QUARTETO) {
                quartetos.put(uniqueId, skyStats);
            } else if (dataType == BED_WARS_RANKED) {
                rankeds.put(uniqueId, skyStats);
            }
        }
    }
}
