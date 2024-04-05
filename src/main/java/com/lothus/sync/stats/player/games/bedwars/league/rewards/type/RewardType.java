package com.lothus.sync.stats.player.games.bedwars.league.rewards.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RewardType {

    COINS("Coin{s}"),
    CASH("Cash"),
    XP("XP"),

    CLOTHE("Roupa{s}"),
    PARTICLE("Particula{s}"),
    DEATH_CRY("Grito{s} de Morte"),
    SLAUGHTER("Mensagem de Abate"),

    RANK("Rank"),
    TAG("Tag");

    String name;
}
