package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Villager extends DeathCry {

    public Villager() {
        super(
                "Villager",
                "deathcry.skywars.villager",
                Sound.VILLAGER_DEATH,
                "http://textures.minecraft.net/texture/ce1fac3d96346e622e890f76ec015a709b673422257b1442061a3aa325982411"
        );
    }

}
