package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Zombie extends DeathCry {

    public Zombie() {
        super(
                "Zumbi",
                "deathcry.skywars.zombie",
                Sound.ZOMBIE_DEATH,
                "http://textures.minecraft.net/texture/c5940044592055f0d3531a19c3f8cedc196b91e371f0d1edb58da5a9a78623a7"
        );
    }

}
