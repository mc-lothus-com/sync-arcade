package com.lothus.sync.stats.player.games.bedwars.league.rewards;

import com.lothus.sync.stats.player.games.bedwars.league.rewards.type.RewardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BedWarsReward {

    private RewardType type;
    private String value;

    private int amount;

}
