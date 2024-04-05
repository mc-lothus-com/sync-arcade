package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Pig extends DeathCry {

    public Pig() {
        super(
                "Porco",
                "deathcry.skywars.porco",
                Sound.PIG_DEATH,
                "http://textures.minecraft.net/texture/bee8514892f3d78a32e8456fcbb8c6081e21b246d82f398bd969fec19d3c27b3"
        );
    }

}
