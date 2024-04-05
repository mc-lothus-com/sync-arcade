package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class EnderDragon extends DeathCry {

    public EnderDragon() {
        super(
                "Ender Dragon",
                "deathcry.skywars.enderdragon",
                Sound.ENDERDRAGON_HIT,
                "http://textures.minecraft.net/texture/74ecc040785e54663e855ef0486da72154d69bb4b7424b7381ccf95b095a"
        );
    }

}
