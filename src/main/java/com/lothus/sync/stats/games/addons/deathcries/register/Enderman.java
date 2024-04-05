package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Enderman extends DeathCry {

    public Enderman() {
        super(
                "Enderman",
                "deathcry.skywars.enderman",
                Sound.ENDERMAN_DEATH,
                "http://textures.minecraft.net/texture/f8e53e9a34fd1eba83b4342e45745beea1673755f5ad4135d2eae97a4afe2b2d"
        );
    }

}
