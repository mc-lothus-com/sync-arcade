package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Chicken extends DeathCry {

    public Chicken() {
        super(
                "Galinha",
                "deathcry.skywars.Chicken",
                Sound.CHICKEN_HURT,
                "http://textures.minecraft.net/texture/4156dad2226d5c85b146f6099223a246468d8592e554281636df0d1219c44f97"
        );
    }

}
