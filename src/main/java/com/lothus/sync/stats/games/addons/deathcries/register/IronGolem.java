package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class IronGolem extends DeathCry {

    public IronGolem() {
        super(
                "Golem de Ferro",
                "deathcry.skywars.irongolem",
                Sound.IRONGOLEM_DEATH,
                "http://textures.minecraft.net/texture/e13f34227283796bc017244cb46557d64bd562fa9dab0e12af5d23ad699cf697"
        );
    }

}
