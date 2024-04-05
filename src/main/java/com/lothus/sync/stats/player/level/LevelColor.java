package com.lothus.sync.stats.player.level;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LevelColor {

    Unranked("§7", "-",  0, 9),
    Bronze("§6" , "✪", 10, 19),
    Silver("§8", "☆", 20, 29),
    Gold("§e", "✫",30, 39),
    Platinum("§b", "★",  40, 49),
    Diamond("§3", "✶", 50, 59),
    Master("§5", "✴", 60, 69),
    Grandmaster("§d", "✷",  70, 79),
    Challenger("§4", "✣", 80, 89),
    Legendary("§c", "✤",  90, 99),
    Mythical("§a", "✻",  100, 109),
    Godlike("§2", "✽",110, 119),
    Immortal("§9", "✱", 120, 129),
    Divine("§6", "♛",  130, 99999),;

    String color;
    String symbol;
    int startLevel;
    int endLevel;

    public static LevelColor getLevelColor(int level) {
        for (LevelColor levelColor : values()) {
            if (level >= levelColor.getStartLevel() && level <= levelColor.getEndLevel()) {
                return levelColor;
            }
        }
        return Unranked;
    }

    public static LevelColor nextLevel(LevelColor color) {
        for (int i = 0; i < values().length; i++) {
            if (values()[i] == color) {
                return values()[i + 1];
            }
        }
        return Divine;
    }

}
