package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class ZombiePig extends DeathCry {
    public ZombiePig() {
        super(
                "Porco Zumbi",
                "deathcry.skywars.zombiepig",
                Sound.ZOMBIE_PIG_DEATH,
                "http://textures.minecraft.net/texture/5e00b73332d5d76c7476680296b4c36cbfd7ac5024632b72b5f045648a4e05db"
        );
    }
}
