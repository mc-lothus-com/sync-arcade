package com.lothus.sync.stats.player.games.skywars.stats.ranked;

import com.lothus.sync.stats.player.games.skywars.stats.ranked.league.League;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public  class SkyRanked {

    private UUID uniqueId;

    private int kills;
    private int loses;

    private int games;

    private int wins;
    private int bestWinstreak;
    private int currentWinstreak;

    private int points;
    private League league;

    public SkyRanked(UUID uniqueId) {
        this.uniqueId = uniqueId;

        kills = 0;
        loses = 0;

        wins = 0;
        bestWinstreak = 0;
        currentWinstreak = 0;

        points = 0;

        league = League.K;
    }
}
