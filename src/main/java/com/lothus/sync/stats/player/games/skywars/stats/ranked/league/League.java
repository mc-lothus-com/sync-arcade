package com.lothus.sync.stats.player.games.skywars.stats.ranked.league;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum League {

    A("[A]", 16000),
    B("[B]", 14000),
    C("[C]", 12000),
    D("[D]", 10000),
    E("[E]", 8000),
    F("[F]", 6000),
    H("[G]", 4000),
    I("[I]", 2000),
    J("[J]", 1000),
    K("[K]", 0);

    String name;
    int points;
}
