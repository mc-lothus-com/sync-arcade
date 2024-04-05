package com.lothus.sync.stats.player.games.bedwars.league;

import com.lothus.core.player.group.rank.Rank;
import com.lothus.sync.stats.player.games.bedwars.league.rewards.BedWarsReward;
import com.lothus.sync.stats.player.games.bedwars.league.rewards.type.RewardType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum BedWarsLeague {

    INICIANTE(1,"§7", "Iniciante", "★", 0, null),
    APRENDIZ(2,"§b", "Aprendiz", "☆", 1000,  Arrays.asList(new BedWarsReward(RewardType.COINS, "1000", 1000), new BedWarsReward(RewardType.XP, "100", 100))),
    EXPLORADOR(3,   "§2", "Explorador", "✮", 1500, Arrays.asList(new BedWarsReward(RewardType.COINS, "2000", 2000), new BedWarsReward(RewardType.XP, "200", 200), new BedWarsReward(RewardType.TAG, "VIP", 2))),
    AVENTUREIRO(4,"§6", "Aventureiro", "✯", 2000,  Arrays.asList(new BedWarsReward(RewardType.COINS, "3000", 3000), new BedWarsReward(RewardType.XP, "300", 300), new BedWarsReward(RewardType.TAG, "PRO", 2))),
    VISIONARIO(5,"§c", "Visionário", "✰", 2000, Arrays.asList(new BedWarsReward(RewardType.COINS, "4000", 4000), new BedWarsReward(RewardType.XP, "400", 400), new BedWarsReward(RewardType.TAG, "PRO", 7))),
    EXPERIENTE(6, "§a", "Experiente", "❂", 2500, Arrays.asList(new BedWarsReward(RewardType.COINS, "5000", 5000), new BedWarsReward(RewardType.XP, "500", 500))),
    MESTRE(7, "§6", "Mestre", "❉", 3000, Arrays.asList(new BedWarsReward(RewardType.COINS, "6000", 6000), new BedWarsReward(RewardType.XP, "600", 600), new BedWarsReward(RewardType.TAG, "NATAL", 7))),
    PRODIGIO(8,"§9", "Prodígio", "❊", 3500, Arrays.asList(new BedWarsReward(RewardType.COINS, "7000", 7000), new BedWarsReward(RewardType.XP, "700", 700))),
    LENDA(9, "§5", "Lenda", "❋", 4000, Arrays.asList(new BedWarsReward(RewardType.COINS, "8000", 8000), new BedWarsReward(RewardType.XP, "800", 800))),
    SABIO(10,"§2", "Sábio", "❖", 5000, Arrays.asList(new BedWarsReward(RewardType.COINS, "9000", 9000), new BedWarsReward(RewardType.XP, "900", 900), new BedWarsReward(RewardType.TAG, Rank.LOTHUS.name(), 15)));

    int id;
    String color;
    String name;
    String symbol;
    int points;
    List<BedWarsReward> rewards;


    public static BedWarsLeague getLeague(int id) {
        for (BedWarsLeague league : values()) {
            if (league.getId() == id) {
                return league;
            }
        }
        return INICIANTE;
    }

    public static String getTag(BedWarsLeague league) {
        return league.getColor() + "(" + league.getSymbol() + ") " + league.getName();
    }

    public static BedWarsLeague nextLevel(BedWarsLeague league) {
        for (int i = 0; i < values().length; i++) {
            if (values()[i] == league) {
                return values()[i + 1];
            }
        }
        return SABIO;
    }

}
