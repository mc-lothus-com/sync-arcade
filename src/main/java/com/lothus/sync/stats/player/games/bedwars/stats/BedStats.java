package com.lothus.sync.stats.player.games.bedwars.stats;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Getter @Setter
public class BedStats {

    private UUID uniqueId;

    private int kills;
    private int deaths;

    private int finalKills;
    private int finalDeaths;

    private int destroyedBeds;
    private int lossBeds;

    private int games;
    private int wins;
    private int loses;

    private int currentWinstreak;
    private int bestWinstreak;

    public BedStats(UUID uniqueId) {
        this.uniqueId = uniqueId;

        kills = 0;
        deaths = 0;

        finalDeaths = 0;
        finalKills = 0;

        destroyedBeds = 0;
        lossBeds = 0;

        games = 0;
        wins = 0;

        loses = 0;

        currentWinstreak = 0;
        bestWinstreak = 0;
    }
}
