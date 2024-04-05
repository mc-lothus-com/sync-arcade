package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Creeper extends DeathCry {

    public Creeper() {
        super(
                "Creeper",
                "deathcry.skywars.creeper",
                Sound.CREEPER_DEATH,
                "http://textures.minecraft.net/texture/1ff8f6d00d5b07387584f117c66d698c90c69cedb01a6e69dbb02771c7302d16"
        );
    }

}
