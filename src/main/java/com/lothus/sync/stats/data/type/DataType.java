package com.lothus.sync.stats.data.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataType {

    BED_WARS_ACCOUNT("bedwars", "players", "bedwarsPlayer"),
    BED_WARS_SOLO("bedwars", "solo", "bedwarsSolo"),
    BED_WARS_TEAM("bedwars", "team", "bedwarsTeam"),
    BED_WARS_TRIO("bedwars", "trio", "bedwarsTrio"),
    BED_WARS_QUARTETO("bedwars", "quarteto", "bedwarsQuarteto"),
    BED_WARS_RANKED("bedwars", "ranked", "bedwarsRanked"),

    SKY_WARS_ACCOUNT("skywars", "players", "skywarsPlayer"),
    SKY_WARS_SOLO("skywars", "solo", "skywarsSolo"),
    SKY_WARS_TEAM("skywars", "team", "skywarsTeam"),
    SKY_WARS_RANKED("skywars", "ranked", "skywarsRanked");

    String database, collection, redisPrefix;

    public static DataType get(String name) {
        for (DataType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

    public static boolean exists(String name) {
        return get(name) != null;
    }
}
