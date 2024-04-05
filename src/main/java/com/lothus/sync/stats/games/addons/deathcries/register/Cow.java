package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Cow extends DeathCry {

    public Cow() {
        super(
                "Vaca",
                "deathcry.skywars.cow",
                Sound.COW_HURT,
                "http://textures.minecraft.net/texture/d6551840955f524367580f11b35228938b6786397a8f2e8c8cc6b0eb01b5db3d"
        );
    }

}
