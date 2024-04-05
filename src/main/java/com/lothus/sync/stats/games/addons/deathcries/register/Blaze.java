package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Blaze extends DeathCry {

    public Blaze() {
        super(
                "Blaze",
                "deathcry.skywars.blaze",
                Sound.BLAZE_DEATH,
                "http://textures.minecraft.net/texture/b20657e24b56e1b2f8fc219da1de788c0c24f36388b1a409d0cd2d8dba44aa3b"
        );
    }

}
