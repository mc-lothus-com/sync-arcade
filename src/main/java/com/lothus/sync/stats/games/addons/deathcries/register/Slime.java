package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Slime extends DeathCry {

    public Slime() {
        super(
                "Slime",
                "deathcry.skywars.slime",
                Sound.SLIME_ATTACK,
                "http://textures.minecraft.net/texture/23fbea287b3a672ee324c720e77af8f730f851d20dad9ff1fa1c051ede5bc813"
        );
    }

}
