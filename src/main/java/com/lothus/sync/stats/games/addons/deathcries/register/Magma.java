package com.lothus.sync.stats.games.addons.deathcries.register;

import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import org.bukkit.Sound;

public class Magma extends DeathCry {

    public Magma() {
        super(
                "Cubo de Magma",
                "deathcry.skywars.magmacube",
                Sound.MAGMACUBE_JUMP,
                "http://textures.minecraft.net/texture/a1c97a06efde04d00287bf20416404ab2103e10f08623087e1b0c1264a1c0f0c"
        );
    }

}
