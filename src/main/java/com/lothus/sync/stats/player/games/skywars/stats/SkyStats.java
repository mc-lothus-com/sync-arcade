package com.lothus.sync.stats.player.games.skywars.stats;

import com.lothus.core.Core;
import com.lothus.core.games.room.RoomType;
import com.lothus.core.player.LothPlayer;
import com.lothus.sync.stats.games.addons.ability.Ability;
import com.lothus.sync.stats.games.addons.kit.Kit;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class SkyStats {

    private UUID uniqueId;

    private int kills;
    private int deaths;

    private int games;
    private int wins;
    private int loses;

    private int currentWinstreak;
    private int bestWinstreak;

    private String kit;
    private String ability;

    public SkyStats(UUID uniqueId) {
        this.uniqueId = uniqueId;

        kills = 0;
        deaths = 0;

        games = 0;
        wins = 0;

        loses = 0;

        currentWinstreak = 0;
        bestWinstreak = 0;

        kit = "None";
        ability = "None";
    }

    public boolean hasAbility(RoomType roomType, Ability ability) {
        LothPlayer lothPlayer = Core.getPlayerController().get(uniqueId);
        return lothPlayer.getGroup().containsPermission((roomType == RoomType.SOLO ? ability.getSoloPermission() : ability.getTeamPermission()));
    }

    public boolean hasKit(RoomType roomType, Kit kit) {
        LothPlayer lothPlayer = Core.getPlayerController().get(uniqueId);
        return lothPlayer.getGroup().containsPermission((roomType == RoomType.SOLO ? kit.getSoloPermission() : kit.getTeamPermission()));
    }
}
