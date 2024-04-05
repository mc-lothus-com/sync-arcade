package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Skeleton extends DeathCry {

    public Skeleton() {
        super(
                "Esqueleto",
                "deathcry.skywars.skeleton",
                Sound.SKELETON_DEATH,
                "http://textures.minecraft.net/texture/9d46eb642dc3a4dfbb5ad5297edae2996ea4cfff92ac2eb56dfae9ee1d58e408"
        );
    }

}
