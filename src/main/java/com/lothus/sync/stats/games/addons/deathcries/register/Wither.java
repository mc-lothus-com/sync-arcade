package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Wither extends DeathCry {

    public Wither() {
        super(
                "Wither",
                "deathcry.skywars.wither",
                Sound.WITHER_DEATH,
                "http://textures.minecraft.net/texture/3fa9831122746556daf303d8d5e5e656d6a028b33dcadb205ace5ba37973b354"
        );
    }

}
