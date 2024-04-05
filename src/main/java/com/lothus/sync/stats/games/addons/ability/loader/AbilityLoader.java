package com.lothus.sync.stats.games.addons.ability.loader;

import com.lothus.core.utils.bukkit.classes.ClassGetter;
import com.lothus.sync.stats.games.addons.ability.Ability;
import com.lothus.sync.stats.platform.Platform;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AbilityLoader {

    public static void loadAbility(JavaPlugin plugin, String path) {
        for (Class<?> death : ClassGetter.getClassesForPackage(plugin, path)) {
            if (Ability.class.isAssignableFrom(death)) {
                try {
                    Ability k = (Ability) death.newInstance();
                    Bukkit.getPluginManager().registerEvents(k, plugin);
                    Platform.getSkyPlatform().getAbilityController().load(k);
                } catch (Exception e) {

                }
            }
        }
    }
}
