package com.lothus.sync.stats.games.addons.deathcries.loader;

import com.lothus.core.utils.bukkit.classes.ClassGetter;
import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import com.lothus.sync.stats.platform.Platform;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathLoader {

    public static void loadDeaths(JavaPlugin plugin, String path) {
        for (Class<?> death : ClassGetter.getClassesForPackage(plugin, path)) {
            if (DeathCry.class.isAssignableFrom(death)) {
                try {
                    DeathCry k = (DeathCry) death.newInstance();
                    Platform.getDeathController().load(k);
                } catch (Exception e) {

                }
            }
        }
    }
}
